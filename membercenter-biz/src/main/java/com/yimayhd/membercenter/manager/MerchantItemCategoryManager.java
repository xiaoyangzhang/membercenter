package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.constant.MemberConstant;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.domain.examine.ExamineDetailDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.query.MerchantItemQuery;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.dao.MerchantItemCategoryDao;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.mapper.ExamineDetailDOMapper;
import com.yimayhd.membercenter.mapper.MerchantCategoryDOMapper;
import com.yimayhd.membercenter.mq.MsgSender;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.dto.MerchantUserDTO;

public class MerchantItemCategoryManager {
    private static final Logger logger = LoggerFactory.getLogger(MerchantItemCategoryManager.class);

    @Autowired
    private MerchantItemCategoryDao merchantItemCategoryDao;
    @Autowired
    private MsgSender msgSender;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private MerchantRepo merchantRepo;
    @Autowired
    private TalentExamineManager talentExamineManager;
    @Autowired
    ExamineDOMapper examineDOMapper;
    @Autowired
    IDPool examineDetailIdPool;
    @Autowired
    ExamineDetailDOMapper examineDetailDOMapper;
    @Autowired
    private MerchantCategoryDOMapper merchantCategoryDOMapper;

    public List<MerchantItemCategoryDO> findMerchantItemCategoryByMerchant(int domainId, long sellerId) {
        return merchantItemCategoryDao.selectMerchantItemCategoriesByMerchant(domainId, sellerId);
    }

    /**
     * 审批商家入驻申请，保存商家对应的类目权限
     * @param examineDO
     * @param categoryIds
     * @return
     */
    public MemResultSupport saveMerchanItemCategories(final ExamineDO examineDO, final long[] categoryIds) {
        MemResultSupport memResultSupport = new MemResultSupport();
        // 新增商家
        final MerchantDO merchantDO = ExamineConverter.examineToMerchant(examineDO);
        long merchantCategoryId = examineDO.getMerchantCategoryId() ;
        if( merchantCategoryId > 0 ){
        	MerchantCategoryDO merchantCategoryDO = merchantCategoryDOMapper.selectByPrimaryKey(merchantCategoryId);
        	if( merchantCategoryDO != null && merchantCategoryDO.getMerchantCategoryFeature() != null ){
        		merchantDO.setBusiType(merchantCategoryDO.getMerchantCategoryFeature().getBusinessType());
        	}
        	
        }
        MemResult<MerchantUserDTO> queryResult = merchantRepo.queryMerchantBySellerId(examineDO.getSellerId(), examineDO.getDomainId());
        if (queryResult == null ||  queryResult.getValue() == null || queryResult.getValue().getMerchantDO() == null) {
			
        	MemResult<MerchantDO> memResult = merchantRepo.saveMerchant(merchantDO);
        	if (!memResult.isSuccess()) {
        		logger.error("saveMerchanItemCategories param:{} is null, insertMerchant failure", JSONObject.toJSONString(merchantDO));
        		memResultSupport.setReturnCode(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR);
        		return memResultSupport;
        	}
		}

        // 查询商家的入驻申请
        final MemResult<ExamineDO> examineResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
        final ExamineDO queryExamineDO = examineResult.getValue();

        // 声明商家的入驻申请历史明细
        final ExamineDetailDO examineDetailDO = new ExamineDetailDO();
        BeanUtils.copyProperties(queryExamineDO, examineDetailDO);
        examineDetailDO.setId(examineDetailIdPool.getNewId());
        examineDetailDO.setGmtModified(new Date());

        // 为商家授权、发送短信
        TransactionSendResult merchantItemCategorySendResult = msgSender.sendMessage(queryExamineDO, MemberTopic.MERCHANT_ROLE_BIND.getTopic(),
                MemberTopic.MERCHANT_ROLE_BIND.getTags(), new LocalTransactionExecuter() {
                    @Override
                    public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
                        MemResultSupport mrs = null;
                        try {
                            mrs = transactionTemplate.execute(new TransactionCallback<MemResultSupport>() {
                                @Override
                                public MemResultSupport doInTransaction(TransactionStatus transactionStatus) {

                                    MemResultSupport support = new MemResultSupport();
                                    try {
                                        examineDO.setId(queryExamineDO.getId());
                                        logger.info("========================examineDOMapper.updateByPrimaryKey param:examineDO={}",JSON.toJSONString(examineDO));
                                        int count = examineDOMapper.updateByPrimaryKey(examineDO);
                                        if (count != 1) {
                                            logger.error("saveMerchanItemCategories param:{} is null, update failure", JSONObject.toJSONString(examineDO));
                                            transactionStatus.setRollbackOnly();
                                            support.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                                            return support;
                                        }
                                        logger.info("saveMerchanItemCategories param:{} update success", JSONObject.toJSONString(examineDO));

                                        // 保存审核明细表
                                        count = examineDetailDOMapper.insert(examineDetailDO);
                                        if (count != 1) {
                                            logger.error("saveMerchanItemCategories param:{} is null, insertDetail failure", JSONObject.toJSONString(examineDO));
                                            transactionStatus.setRollbackOnly();
                                            support.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                                            return support;
                                        }
                                        logger.info("saveMerchanItemCategories param:{} insertDetail success", JSONObject.toJSONString(queryExamineDO));

                                        // 添加或更新商家所具有的类目权限
                                        MerchantItemQuery queryDTO = new MerchantItemQuery();
                                        queryDTO.setDomainId(examineDO.getDomainId());
                                        queryDTO.setSellerId(merchantDO.getSellerId());
                                        queryDTO.setStatus(MemberConstant.MEMBER_NOT_IN_USR);
                                        List<MerchantItemCategoryDO> merchantItemCategorys = merchantItemCategoryDao.selectMerchantItemCategory(queryDTO);
                                        //该商家已存在商品类目
                                        if (!CollectionUtils.isEmpty(merchantItemCategorys)) {
											for (MerchantItemCategoryDO mic : merchantItemCategorys) {
												mic.setStatus(MemberConstant.MEMBER_NOT_IN_USR);
											}
											boolean updateNotInUseResult = merchantItemCategoryDao.updateMerchantItemCategory(merchantItemCategorys);
											if (!updateNotInUseResult) {
	                                            logger.error(" param:List<MerchantItemCategoryDO>={} , result:{}", JSONObject.toJSONString(merchantItemCategorys),JSON.toJSONString(updateNotInUseResult));
	                                            transactionStatus.setRollbackOnly();
	                                            support.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
	                                            return support;
	                                        }
											List<MerchantItemCategoryDO> updateList = new ArrayList<MerchantItemCategoryDO>();
											List<Long> idList = new ArrayList<Long>();
											for (long categoryId : categoryIds) {
												int countNum = 0;
												for (MerchantItemCategoryDO m : merchantItemCategorys) {
													if (categoryId == m.getItemCategoryId()) {
														countNum ++ ;
														m.setStatus(MemberConstant.MEMBER_IN_USR);
														updateList.add(m);
													}else {
														continue;
													}
													if (countNum == 0) {
														idList.add(categoryId);
													}
												}
											}
											boolean updateInUseResult = merchantItemCategoryDao.updateMerchantItemCategory(updateList);
											if (!updateInUseResult) {
	                                            logger.error(" param:List<MerchantItemCategoryDO>={} , result:{}", JSONObject.toJSONString(merchantItemCategorys),JSON.toJSONString(updateInUseResult));
	                                            transactionStatus.setRollbackOnly();
	                                            support.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
	                                            return support;
	                                        }
											boolean target = insertMerchantItemCategory(examineDO,categoryIds);
											if (!target) {
												//logger.error("saveMerchanItemCategories param:{} is null, saveMerchanItemCategories failure", JSONObject.toJSONString(merchantItemCategoryDOs));
												transactionStatus.setRollbackOnly();
												support.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
												return support;
											}
										}else {
											
											
											boolean target = insertMerchantItemCategory(examineDO,categoryIds);
											if (!target) {
												//logger.error("saveMerchanItemCategories param:{} is null, saveMerchanItemCategories failure", JSONObject.toJSONString(merchantItemCategoryDOs));
												transactionStatus.setRollbackOnly();
												support.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
												return support;
											}
											//logger.info("saveMerchanItemCategories param:{} saveMerchanItemCategories success", JSONObject.toJSONString(merchantItemCategoryDOs));
										}  } catch (Exception e) {
                                        logger.error("saveMerchanItemCategories failed!  examineDO={},  categoryIds={}", JSON.toJSONString(examineDO), JSON.toJSONString(categoryIds), e);
                                        support.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
                                        transactionStatus.setRollbackOnly();
                                    }
                                    return support;
                                }
                            });
                        } catch (Exception e) {
                            logger.error("saveMerchanItemCategories failed!  examineDO={},  categoryIds={}", JSON.toJSONString(examineDO), JSON.toJSONString(categoryIds), e);
                            mrs.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
                        }
                        if(mrs != null && mrs.isSuccess()) {
                        	return LocalTransactionState.COMMIT_MESSAGE;
                        }
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                });
        if (merchantItemCategorySendResult.getSendStatus() != SendStatus.SEND_OK) {
            logger.error("send msg failed! topic={}, msg={},  result={}", MemberTopic.MERCHANT_ROLE_BIND.getTopic(), JSON.toJSONString(examineDO), JSON.toJSONString(merchantItemCategorySendResult));
            memResultSupport.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }

        return memResultSupport;
    }
    /**
     * 
    * created by zhangxiaoyang
    * @date 2016年8月3日
    * @Title: insertMerchantItemCategory 
    * @Description: 封装要插入的商家和商品类目管理表数据
    * @param @param domainId
    * @param @param categoryId
    * @param @param sellerId
    * @param @return    设定文件 
    * @return MemResultSupport    返回类型 
    * @throws
     */
    private boolean insertMerchantItemCategory(final ExamineDO examineDO,final long[] categoryIds) {
    	ArrayList<MerchantItemCategoryDO> merchantItemCategoryDOs = new ArrayList<>();
		for (long categoryId : categoryIds) {
			MerchantItemCategoryDO merchantItemCategoryDO = new MerchantItemCategoryDO();
			merchantItemCategoryDO.setDomainId(examineDO.getDomainId());
			
			merchantItemCategoryDO.setItemCategoryId(categoryId);
			merchantItemCategoryDO.setSellerId(examineDO.getSellerId());
			merchantItemCategoryDO.setStatus(MemberConstant.MEMBER_IN_USR);
			merchantItemCategoryDOs.add(merchantItemCategoryDO);
		}
		
		try {
			// 保存商家所具有的的类目权限
			boolean target = merchantItemCategoryDao.saveMerchanItemCategories(merchantItemCategoryDOs);
			
			return target;
		} catch (Exception e) {
			logger.error("param:ExamineDO={},categoryIds={},merchantItemCategoryDOs={},error:{}",JSON.toJSONString(examineDO),categoryIds,JSON.toJSONString(merchantItemCategoryDOs),e);
		}
    	return false;
    }
    
    public MemResultSupport checkCategoryPrivilege(int domainId, long categoryId, long sellerId) {
        List<MerchantItemCategoryDO> merchantItemCategoryDOs = merchantItemCategoryDao.selectByCategoryIdAndSellerId(domainId, categoryId, sellerId);
        MemResultSupport support = new MemResultSupport();
        if (merchantItemCategoryDOs.isEmpty()) {
            support.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
        }
        return support;
    }
    public MemResult<MerchantItemCategoryDO> selectObjByCategoryIdAndSellerId(int domainId, long categoryId, long sellerId) {
    	MerchantItemCategoryDO merchantItemCategoryDO = merchantItemCategoryDao.selectObjByCategoryIdAndSellerId(domainId, categoryId, sellerId);
    	MemResult<MerchantItemCategoryDO> result = new MemResult<MerchantItemCategoryDO>();
    	if (merchantItemCategoryDO == null) {
    		result.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
    		return result;
    	}
    	result.setValue(merchantItemCategoryDO);
    	return result;
    }
    @Deprecated
    public MemResult<List<MerchantItemCategoryDO>> getMerchantItemCategory(int domainId, long categoryId, long sellerId) {
    	MemResult<List<MerchantItemCategoryDO>> result = new MemResult<List<MerchantItemCategoryDO>>();
    	List<MerchantItemCategoryDO> merchantItemCategoryDOs = merchantItemCategoryDao.selectByCategoryIdAndSellerId(domainId, categoryId, sellerId);
    	if (merchantItemCategoryDOs.isEmpty()) {
    		result.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
    	}
    	result.setValue(merchantItemCategoryDOs);
    	return result;
    }
}