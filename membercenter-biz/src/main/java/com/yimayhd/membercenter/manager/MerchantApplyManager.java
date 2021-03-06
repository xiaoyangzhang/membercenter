package com.yimayhd.membercenter.manager;

import java.util.Date;
import java.util.List;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dao.examine.MerchantCategoryDao;
import com.yimayhd.membercenter.dao.examine.MerchantScopeDao;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;

/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请manager〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MerchantApplyManager {
	private static final Logger log  = LoggerFactory.getLogger(MerchantApplyManager.class);
	@Autowired
	private TransactionTemplate transactionTemplate ;
	@Autowired
	private IDPool memberIdpool ;
	@Autowired
	private  TalentExamineManager talentExamineManager;
    @Autowired
	private MerchantScopeDao merchantScopeDao;
	@Autowired
	private MerchantCategoryDao merchantCategoryDao;
	
	public MemResult<Boolean> submitExamineInfo(final ExamineInfoDTO examineInfoDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if ((examineInfoDTO == null) ) {
			log.error("param :examineInfoDTO={}",JSON.toJSONString(examineInfoDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		final List<MerchantScopeDO> merchantScopes = examineInfoDTO.getMerchantScopes();
		
				Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {

					@Override
					public Boolean doInTransaction(TransactionStatus status) {
						try {
							//保存数据到审核信息表
							MemResult<Boolean> saveExamineResult = null;
							saveExamineResult = talentExamineManager.submitMerchantExamineInfo(examineInfoDTO);
							if ((saveExamineResult == null) || !saveExamineResult.isSuccess()) {
								status.setRollbackOnly();
								return false;
							}
							MerchantScopeDO merchantScope = new MerchantScopeDO();
							merchantScope.setDomainId(examineInfoDTO.getDomainId());
							merchantScope.setSellerId(examineInfoDTO.getSellerId());
							merchantScope.setStatus(100);
							List<MerchantScopeDO> merchantScopeList = merchantScopeDao.getMerchantScope(merchantScope);
							MerchantScopeDO merScopeDO = null;
							if (!CollectionUtils.isEmpty(merchantScopeList)) {
									for (MerchantScopeDO merchantScopeDO : merchantScopeList) {
										merchantScopeDO.setStatus(100);
										merScopeDO = merchantScopeDao.update(merchantScopeDO);
										if (merScopeDO == null) {
											status.setRollbackOnly();
											return false;
										}
									}
									for (MerchantScopeDO ms : merchantScopes) {
									int count = 0;
									for (MerchantScopeDO merchantScopeDO : merchantScopeList) {
									if (merchantScopeDO.getBusinessScopeId() == ms.getBusinessScopeId()) {
										merchantScopeDO.setStatus(1);
										merScopeDO = merchantScopeDao.update(merchantScopeDO);
										count++;
									}else {
										continue;
									}
//									if (count == 0) {
//										merchantScopeDO.setStatus(100);
//										merScopeDO = merchantScopeDao.update(merchantScopeDO);
//									}
//									if (merScopeDO == null) {
//										status.setRollbackOnly();
//										return false;
//									}
								}
									if (count == 0) {
										merScopeDO = merchantScopeDao.insert(ms);
									}
									if (merScopeDO == null) {
										status.setRollbackOnly();
										return false;
									}
								}
							}else {
								
								for (MerchantScopeDO ms : merchantScopes) {
									
									merScopeDO = merchantScopeDao.insert(ms);
									if (merScopeDO == null) {
										status.setRollbackOnly();
										return false;
									}
								}
							}
		
							return true;
						} catch (Exception e) {

							status.setRollbackOnly(); 
							log.error("param:examineInfoDTO={}   error:{}", JSON.toJSONString(examineInfoDTO) ,e);
							
							return false;
						}
					}
				});
				
		if (dbResult == null || !dbResult) {
			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
			return result;
		}
		return result ;

	}
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryQueryDTO merchantCategoryQueryDTO) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
		MerchantCategoryDO merchantCategory  = new MerchantCategoryDO();
		merchantCategory.setDomainId(merchantCategoryQueryDTO.getDomainId());
		merchantCategory.setId(merchantCategoryQueryDTO.getId());
		List<MerchantCategoryDO> merchantCategoryList = merchantCategoryDao.getMerchantCategory(merchantCategory);
		if (merchantCategoryList == null) {
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
		result.setValue(merchantCategoryList);
		return result;
		
	}
	
}
