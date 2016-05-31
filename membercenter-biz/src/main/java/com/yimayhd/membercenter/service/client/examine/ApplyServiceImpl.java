package com.yimayhd.membercenter.service.client.examine;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.ApplyService;
import com.yimayhd.membercenter.manager.ApplyManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ApplyServiceImpl implements ApplyService {
	
	private static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

	@Autowired
	private ApplyManager applyManager;
	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScopesBySellerId(MerchantScopeDO merchantScope) {
		MemResult<List<BusinessScopeDO>> scopes = new MemResult<List<BusinessScopeDO>>();
		if (merchantScope == null) {
			log.error("params error:merchantScope={}",JSON.toJSONString(merchantScope));
			scopes.setReturnCode(MemberReturnCode.PARAMTER_ERROR);;
			return scopes;
		}
		try {
			  MemResult<List<MerchantScopeDO>> result = applyManager.getMerchantScope(merchantScope);
			  if (result == null || !result.isSuccess() || result.getValue() == null) {
				scopes.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
				return scopes;
			}
			List<Long> idList = new ArrayList<Long>();
			for (MerchantScopeDO msDO : result.getValue()) {
				idList.add(msDO.getId());
			}
			BusinessScopeDO businessScope = new BusinessScopeDO();
			businessScope.setIdList(idList);
			businessScope.setDomainId(merchantScope.getDomainId());
			//businessScope.set
			scopes = applyManager.getBusinessScope(businessScope);
			return scopes;
		} catch (Exception e) {
			log.error("param :merchantScope={} error :{}",JSON.toJSONString(merchantScope),e);
			scopes.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return scopes;
		}
	}

//	@Override
//	public MemResult<List<BusinessScopeDO>> getBusinessScope(BusinessScopeDO businessScope) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
//		if (businessScope == null) {
//			log.error(" param error : businessScope={}",JSON.toJSONString(businessScope));
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
//		try {
//			result = applyManager.getBusinessScope(businessScope);
//			return result;
//		} catch (Exception e) {
//			log.error("param : businessScope={} error :{}",JSON.toJSONString(businessScope),e);
//			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
//			return result;
//		}
//	}
//
//	@Override
//	public MemResult<List<QualificationDO>> getAllQualification(int domainId) {
//		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
//		if (domainId <= 0) {
//			log.error(" param error : domainId={}",domainId);
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
//		try {
//			result = applyManager.getAllQualification(domainId);
//			return result;
//		} catch (Exception e) {
//			log.error("param : domainId={} error :{}",domainId,e);
//			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
//			return result;
//		}
//	}

	@Override
	public MemResult<List<MerchantCategoryScopeDO>> getMerchantCategoryScope(
			MerchantCategoryScopeDO merchantCategoryScope) {
		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
		if ( merchantCategoryScope == null) {
			log.error(" param error : MerchantCategoryScopeDO merchantCategoryScope={}",JSON.toJSONString(merchantCategoryScope));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getMerchantCategoryScope(merchantCategoryScope);
			return result;
		} catch (Exception e) {
			log.error("param : MerchantCategoryScopeDO merchantCategoryScope={} error :{}",JSON.toJSONString(merchantCategoryScope),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(
			CategoryQualificationDO categoryQualificationDO) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();
		if (categoryQualificationDO == null) {
			log.error(" param error : categoryQualificationDO={}",JSON.toJSONString(categoryQualificationDO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getCategoryQualification(categoryQualificationDO);
			return result;
		} catch (Exception e) {
			log.error(" param error : categoryQualificationDO={} error:{}",JSON.toJSONString(categoryQualificationDO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<QualificationDO>> getQualificationBySellerId(
			MerchantQualificationDO merchantQualification) {
		MemResult<List<QualificationDO>> qualifications = new MemResult<List<QualificationDO>>();
		if (merchantQualification == null) {
			log.error(" param error : merchantQualification={} ",JSON.toJSONString(merchantQualification));
			qualifications.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return qualifications;
		}
		try {
			 MemResult<List<MerchantQualificationDO>> result = applyManager.getMerchantQualification(merchantQualification);
			if (result == null || !result.isSuccess() || result.getValue() == null ) {
				qualifications.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
				return qualifications;
			}
			List<Long> idList = new ArrayList<Long>();
			for (MerchantQualificationDO mqDO : result.getValue()) {
				idList.add(mqDO.getId());
			}
			QualificationDO qualification = new QualificationDO();
			qualification.setIdList(idList);
			qualification.setDomainId(merchantQualification.getDomainId());
			//qualification.set
			 qualifications = applyManager.getQualification(qualification);
			return qualifications;
		} catch (Exception e) {
			log.error("param : merchantQualification={} error :{}",JSON.toJSONString(merchantQualification),e);
			qualifications.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return qualifications;
		}
	}

	@Override
	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (dto == null ) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		//List<MerchantScopeDO> merchantScopes = dto.getMerchantScopes();
		try {
			result = applyManager.submitExamineInfo(dto);
			return result;
		} catch (Exception e) {
			log.error("params : ExamineInfoDTO={}   error :{}",JSON.toJSONString(dto),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<Boolean> updateMerchantQualification(
			ExamineInfoDTO dto) {
		
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (dto == null ) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.updateMerchantQualification(dto);
			return result;
		} catch (Exception e) {
			log.error("params : MerchantQualificationDO={}  error :{}",JSON.toJSONString(dto),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<QualificationDO>> getQualification(
			QualificationDO qualification) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (qualification == null) {
			log.error("params error:qualification={} ",JSON.toJSONString(qualification));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getQualification(qualification);
			return result;
		} catch (Exception e) {
			log.error("params :qualification={} error:{}",JSON.toJSONString(qualification),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScope(
			BusinessScopeDO businessScope) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
		if (businessScope == null) {
			log.error("params error:businessScope={}",JSON.toJSONString(businessScope));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		
		try {
			result = applyManager.getBusinessScope(businessScope);
			return result;
		} catch (Exception e) {
			log.error("params :businessScope={} error:{}",JSON.toJSONString(businessScope),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

//	@Override
//	public MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, List<Long> ids) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
//		if (domainId <= 0 || ids == null || ids.size() == 0) {
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//		}
//		result = applyManager.getBusinessScopesByIds(domainId, ids);
//		return result;
//	}

//	public MemResult<List<MerchantCategoryDO>> getAllMerchantCategory(
//			int domainId) {
//		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
//		if (domainId <= 0 ) {
//			log.error("param error:domainId={}",domainId);
//			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
//			return result;
//		}
//		try {
//			result = applyManager.getAllMerchantCategory(domainId);
//			return result;
//		} catch (Exception e) {
//			log.error("params : domainId={} error:{}",domainId,e);
//			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
//			return result;
//		}
//	}

	@Override
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryDO merchantCategory) {
		return applyManager.getMerchantCategory(merchantCategory);
	}

}
