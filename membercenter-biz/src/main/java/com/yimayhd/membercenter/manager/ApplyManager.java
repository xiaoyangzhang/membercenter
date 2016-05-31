package com.yimayhd.membercenter.manager;

import java.util.List;

import com.yimayhd.membercenter.entity.merchant.Merchant;
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
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
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
public class ApplyManager {
	private static final Logger log  = LoggerFactory.getLogger(ApplyManager.class);
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
	public MemResult<List<BusinessScopeDO>> getAllBusinessScope(int domainId) {
		//List<BusinessScopeDO> businessScopeList = new ArrayList<BusinessScopeDO>();
//		if (domainId <= 0) {
//			
//			return MemResult.buildFailResult(-1, "参数错误", null);
//		}
		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
		List<BusinessScopeDO> businessScopeList = businessScopeDao.getAllBusinessScope(domainId);
		 if(businessScopeList == null) {
			 result.setReturnCode(MemberReturnCode.BUSINESS_SCOPE_FAILED);
			 return result;
		 }
		 result.setValue(businessScopeList);
		 return result;

	}

	public MemResult<List<QualificationDO>> getAllQualification(int domainId) {
		//List<QualificationDO> qualificationList = new ArrayList<QualificationDO>();
//		if (domainId <= 0) {
//			return MemResult.buildFailResult(-1, "参数错误", null);
//		}
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		List<QualificationDO> qualificationList = qualificationDao.getAllQualification(domainId);
		if (qualificationList == null) {
			result.setReturnCode(MemberReturnCode.QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualificationList);
		return result;
	}

	public MemResult<List<CategoryQualificationDO>> getQualificationByMerchantCategoryId(long merchantCategoryId,int domainId) {
//		if (merchantCategoryId <=0 || domainId <= 0) {
//			return MemResult.buildFailResult(-1, "参数错误", null);
//		}
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();

		List<CategoryQualificationDO> qualifications = categoryQualificationDao.getCATGQualificationByMerchantCATGId(merchantCategoryId, domainId);
		if (qualifications == null) {
			result.setReturnCode(MemberReturnCode.CATEGORY_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualifications);
		return result;

	}
	public MemResult<List<MerchantCategoryScopeDO>> getScopeByMerchantCategoryId(long merchantCategoryId,int domainId) {
//		if (merchantCategoryId <=0 || domainId <= 0) {
//			return MemResult.buildFailResult(-1, "参数错误", null);
//		}
		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
		List<MerchantCategoryScopeDO> scopes = merchantCategoryScopeDao.getMerchantCategoryScopeByMerchantCategoryId(merchantCategoryId, domainId);
		if (scopes == null) {
			result.setReturnCode(MemberReturnCode.CATEGORY_BUSINESS_SCOPE_FAILED);
			return result;
		}
		result.setValue(scopes);
		return result;
	}
	public MemResult<List<MerchantScopeDO>> getBusinessScopeBySellerId(long sellerId,int domainId) {
//		if (sellerId <=0 || domainId <= 0) {
//			return MemResult.buildFailResult(-1, "参数错误", null);
//		}
		MemResult<List<MerchantScopeDO>> result = new MemResult<List<MerchantScopeDO>>();
		List<MerchantScopeDO> merchantScopes = merchantScopeDao.getMerchantScopeBySellerId(sellerId, domainId);
		if (merchantScopes == null) {
			result.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
			return result;
		}
		result.setValue(merchantScopes);
		return result;
	}

	public MemResult<List<MerchantQualificationDO>> getMerchantQualificationBySellerId(long sellerId,int domainId) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		List<MerchantQualificationDO> merchantQualifications = merchantQualificationDao.getMerchantQualificationBySellerId(sellerId, domainId);
		if (merchantQualifications == null) {
			result.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(merchantQualifications);
		return result;
	}

	
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
							MemResult<Boolean> saveExamineResult = null;
							saveExamineResult = talentExamineManager.submitMerchantExamineInfo(examineInfoDTO);
							if ((saveExamineResult == null) || !saveExamineResult.isSuccess()) {
								status.setRollbackOnly();
								return false;
							}
		
							MerchantScopeDO merScopeDO = null;
							for (MerchantScopeDO ms : merchantScopes) {
								
								if (ms.getId() <= 0) {
									merScopeDO = merchantScopeDao.insert(ms);
								}else {
									merScopeDO = merchantScopeDao.update(ms);
								}
								if (merScopeDO == null) {
									status.setRollbackOnly();
									return false;
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

	public MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, List<Long> ids) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<>();

		List<BusinessScopeDO> businessScopeDOs = businessScopeDao.getBusinessScopesByScope(domainId,ids);
		if(businessScopeDOs.isEmpty()) {
			result.setReturnCode(MemberReturnCode.BUSINESS_SCOPE_FAILED);
			return result;
		}
		result.setValue(businessScopeDOs);
		return result;
	}
	
	public MemResult<Boolean> updateMerchantQualification(ExamineInfoDTO examineInfoDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (examineInfoDTO == null) {
			log.error("param error : merchantQualificationDO={}",JSON.toJSONString(examineInfoDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
//		boolean isNewMerchantQualification = false;
//		if (merchantQualificationDO.getId() <= 0) {
//			long id = memberIdpool.getNewId();
//			merchantQualificationDO.setId(id);
//			isNewMerchantQualification = true;
//		}
		List<MerchantQualificationDO> merchantQualifications = examineInfoDTO.getMerchantQualifications();
		MerchantQualificationDO merQuaDO = null;
		for (MerchantQualificationDO mq : merchantQualifications) {
			
			if (mq.getId() <= 0) {
				merQuaDO = merchantQualificationDao.insert(mq);
			}else {
				merQuaDO = merchantQualificationDao.update(mq);
			}
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
	public MemResult<List<MerchantCategoryDO>> getAllMerchantCategory(int domainId) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
		if (domainId <= 0) {
			log.error("param :domainId={}",domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		List<MerchantCategoryDO> merchantCategories = merchantCategoryDao.getAllMerchantCategory(domainId);
		if (merchantCategories == null) {
			result.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
			return result;
		}
		result.setValue(merchantCategories);
		return result;
	}
	public MemResult<List<QualificationDO>> getQualificationByIds(List<Long> idList,int domainId) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (idList == null || domainId <= 0 ) {
			log.error("params error:idList={} domainId={}",idList,domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			List<QualificationDO> qualifications = qualificationDao.getQualificationByIds(idList, domainId);
			if (qualifications == null) {
				log.error("result:qualifications={}",qualifications);
				result.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
				return result;
			}
			result.setValue(qualifications);
			return result;
		} catch (Exception e) {
			log.error("params:idList={} domainId={} error:{}",idList,domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	public MemResult<MerchantCategoryDO> getMerchantCategory(long id, int domianId) {
		MemResult<MerchantCategoryDO> result = new MemResult<>();
		MerchantCategoryDO merchantCategoryDO = merchantCategoryDao.getMerchantCategoriesById(domianId, id);
		if(null == merchantCategoryDO) {
			result.setReturnCode(MemberReturnCode.BUSINESS_CATEGORY_FAILED);
			return result;
		}
		result.setValue(merchantCategoryDao.getMerchantCategoriesById(domianId, id));
		return result;
	}
}
