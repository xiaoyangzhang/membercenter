package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yimayhd.membercenter.entity.merchant.Merchant;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.dao.examine.BusinessScopeDao;
import com.yimayhd.membercenter.dao.examine.CategoryQualificationDao;
import com.yimayhd.membercenter.dao.examine.MerchantApplyDao;
import com.yimayhd.membercenter.dao.examine.MerchantCategoryDao;
import com.yimayhd.membercenter.dao.examine.MerchantCategoryScopeDao;
import com.yimayhd.membercenter.dao.examine.MerchantQualificationDao;
import com.yimayhd.membercenter.dao.examine.MerchantScopeDao;
import com.yimayhd.membercenter.dao.examine.QualificationDao;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.mq.MsgSender;

/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请manager〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class QualificationManager {
	private static final Logger log  = LoggerFactory.getLogger(QualificationManager.class);
	@Autowired
	private  MerchantApplyDao merchantApplyDao;
	@Autowired
	private BusinessScopeDao businessScopeDao;
	@Autowired
	private QualificationDao qualificationDao;
	@Autowired
	private CategoryQualificationDao categoryQualificationDao;
	@Autowired
	private MerchantCategoryScopeDao merchantCategoryScopeDao;
	@Autowired
	private MsgSender msgSender;
	@Autowired
	private TransactionTemplate transactionTemplate ;
	@Autowired
	private IDPool memberIdpool ;
	@Autowired
	private  TalentExamineManager talentExamineManager;
    @Autowired
	private MerchantScopeDao merchantScopeDao;
	@Autowired
	private MerchantQualificationDao merchantQualificationDao;
	@Autowired
	private MerchantCategoryDao merchantCategoryDao;
	

	public MemResult<List<QualificationDO>> getQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		List<QualificationDO> qualificationList = qualificationDao.getQualification(queryDTO);
		if (qualificationList == null) {
			result.setReturnCode(MemberReturnCode.QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualificationList);
		return result;
	}

	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();

		List<CategoryQualificationDO> qualifications = categoryQualificationDao.getCategoryQualification(queryDTO);
		if (qualifications == null) {
			result.setReturnCode(MemberReturnCode.CATEGORY_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualifications);
		return result;

	}
//	public MemResult<List<MerchantCategoryScopeDO>> getMerchantCategoryScope(MerchantCategoryScopeDO merchantCategoryScope,List<Long> idList) {
////		if (merchantCategoryId <=0 || domainId <= 0) {
////			return MemResult.buildFailResult(-1, "参数错误", null);
////		}
//		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
//		List<MerchantCategoryScopeDO> scopes = merchantCategoryScopeDao.getMerchantCategoryScope(merchantCategoryScope,idList);
//		if (scopes == null) {
//			result.setReturnCode(MemberReturnCode.CATEGORY_BUSINESS_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(scopes);
//		return result;
//	}
//	public MemResult<List<MerchantScopeDO>> getMerchantScope(MerchantScopeDO merchantScope) {
////		if (sellerId <=0 || domainId <= 0) {
////			return MemResult.buildFailResult(-1, "参数错误", null);
////		}
//		MemResult<List<MerchantScopeDO>> result = new MemResult<List<MerchantScopeDO>>();
//		List<MerchantScopeDO> merchantScopes = merchantScopeDao.getMerchantScope(merchantScope);
//		if (merchantScopes == null) {
//			result.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(merchantScopes);
//		return result;
//	}

	public MemResult<List<MerchantQualificationDO>> getMerchantQualification(QualificationQueryDTO qualificationQueryDTO) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		List<MerchantQualificationDO> merchantQualifications = merchantQualificationDao.getMerchantQualification(qualificationQueryDTO);
		if (merchantQualifications == null) {
			result.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(merchantQualifications);
		return result;
	}

	
//	public MemResult<Boolean> submitExamineInfo(final ExamineInfoDTO examineInfoDTO) {
//		MemResult<Boolean> result = new MemResult<Boolean>();
//		if ((examineInfoDTO == null) ) {
//			log.error("param :examineInfoDTO={}",JSON.toJSONString(examineInfoDTO));
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
//		final List<MerchantScopeDO> merchantScopes = examineInfoDTO.getMerchantScopes();
//		
//		
//				Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
//
//					@Override
//					public Boolean doInTransaction(TransactionStatus status) {
//						try {
//							MemResult<Boolean> saveExamineResult = null;
//							saveExamineResult = talentExamineManager.submitMerchantExamineInfo(examineInfoDTO);
//							if ((saveExamineResult == null) || !saveExamineResult.isSuccess()) {
//								status.setRollbackOnly();
//								return false;
//							}
//		
//							MerchantScopeDO merScopeDO = null;
//							for (MerchantScopeDO ms : merchantScopes) {
//								
//								if (ms.getId() <= 0) {
//									merScopeDO = merchantScopeDao.insert(ms);
//								}else {
//									merScopeDO = merchantScopeDao.update(ms);
//								}
//								if (merScopeDO == null) {
//									status.setRollbackOnly();
//									return false;
//								}
//							}
//							return true;
//						} catch (Exception e) {
//
//							status.setRollbackOnly(); 
//							log.error("param:examineInfoDTO={}   error:{}", JSON.toJSONString(examineInfoDTO) ,e);
//							
//							return false;
//						}
//					}
//				});
//				
//		if (dbResult == null || !dbResult) {
//			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
//			return result;
//		}
//		return result ;
//
//	}
//
//	public MemResult<List<BusinessScopeDO>> getBusinessScopes(BusinessScopeDO businessScope,List<Long> idList) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
//
//		List<BusinessScopeDO> businessScopeDOs = businessScopeDao.getBusinessScope(businessScope,idList);
//		if(businessScopeDOs.isEmpty()) {
//			result.setReturnCode(MemberReturnCode.BUSINESS_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(businessScopeDOs);
//		return result;
//	}
//	
	public MemResult<Boolean> updateMerchantQualification(ExamineInfoDTO examineInfoDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (examineInfoDTO == null) {
			log.error("param error : merchantQualificationDO={}",JSON.toJSONString(examineInfoDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		List<MerchantQualificationDO> merchantQualifications = examineInfoDTO.getMerchantQualifications();
		MerchantQualificationDO merQuaDO = null;
		for (MerchantQualificationDO mq : merchantQualifications) {
			
			//if (mq.getId() <= 0) {
				merQuaDO = merchantQualificationDao.insert(mq);
//			}else {
//				merQuaDO = merchantQualificationDao.update(mq);
//			}
			if(merQuaDO == null) {
				result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
				return result;
			}
		}
		return result;
	}
	
//	public MemResult<List<BusinessScopeDO>> getBusinessScopeByIds(int domainId,List<Long> idList) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
//		if (idList == null) {
//			log.error("param :idList={}",idList);
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
////		Long [] idArr = new Long [idList.size()];
////		idArr = idList.toArray(idArr);
////		long [] arrays = idArr;
//		List<BusinessScopeDO> businessScopes = businessScopeDao.getBusinessScopesByScope(domainId,idList );
//		if (businessScopes == null) {
//			result.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(businessScopes);
//		return result;
//	}
//	public MemResult<List<MerchantCategoryDO>> getAllMerchantCategory(int domainId) {
//		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
//		if (domainId <= 0) {
//			log.error("param :domainId={}",domainId);
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
//		List<MerchantCategoryDO> merchantCategories = merchantCategoryDao.getAllMerchantCategory(domainId);
//		if (merchantCategories == null) {
//			result.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(merchantCategories);
//		return result;
//	}
	/*public MemResult<List<QualificationDO>> getQualificationAfterMer(QualificationDO qualification) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (qualification == null  ) {
			log.error("params error:qualification={} ",JSON.toJSONString(qualification));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			List<QualificationDO> qualifications = qualificationDao.getQualification(qualification);
			if (qualifications == null) {
				log.error("result:qualifications={}",qualifications);
				result.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
				return result;
			}
			result.setValue(qualifications);
			return result;
		} catch (Exception e) {
			log.error("params :qualification={} error:{} ",JSON.toJSONString(qualification),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}*/

//	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryDO merchantCategory) {
//		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
//		List<MerchantCategoryDO> merchantCategoryDO = merchantCategoryDao.getMerchantCategoriesById(merchantCategory);
//		if(null == merchantCategoryDO) {
//			result.setReturnCode(MemberReturnCode.BUSINESS_CATEGORY_FAILED);
//			return result;
//		}
//		result.setValue(merchantCategoryDO);
//		return result;
//	}
	public MemResult<Boolean> updateMerchantQualificationStatus(QualificationQueryDTO queryDTO) {
		 MemResult<Boolean> result  = new  MemResult<Boolean>();
		MerchantQualificationDO merchantQualification = MerchantConverter.convertQueryQualification2DO(queryDTO);
		MerchantQualificationDO updateResult = merchantQualificationDao.update(merchantQualification);
		if (updateResult == null) {
			result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			
		}
		return result;
	}
	
	public MemResult<Integer> updateStatusBatch(List<QualificationQueryDTO> queryDTOs) {
		 MemResult<Integer> result  = new  MemResult<Integer>();
		 List<MerchantQualificationDO> qualificationDOList = new ArrayList<MerchantQualificationDO>();
		 for (QualificationQueryDTO qualificationQueryDTO : queryDTOs) {
				MerchantQualificationDO merchantQualification = MerchantConverter.convertQueryQualification2DO(qualificationQueryDTO);
				merchantQualification.setGmtModified(new Date());
				qualificationDOList.add(merchantQualification);
		}
		 int updateCount = merchantQualificationDao.updateStatusBatch(qualificationDOList);
		 if (updateCount < queryDTOs.size()) {
			 result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			 return result;
		}
		 result.setValue(updateCount);
		 return result;
	}
}
