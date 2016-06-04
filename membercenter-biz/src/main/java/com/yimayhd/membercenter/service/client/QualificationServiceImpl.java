package com.yimayhd.membercenter.service.client;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.QualificationService;
import com.yimayhd.membercenter.client.service.examine.ApplyService;
import com.yimayhd.membercenter.manager.ApplyManager;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QualificationServiceImpl implements QualificationService {
	
	private static final Logger log = LoggerFactory.getLogger(QualificationServiceImpl.class);

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
			if (idList.size() == 0) {
				scopes.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
				return scopes;
			}
			BusinessScopeDO businessScope = new BusinessScopeDO();
			//businessScope.setIdList(idList);
			businessScope.setDomainId(merchantScope.getDomainId());
			//businessScope.set
			scopes = applyManager.getBusinessScope(businessScope,idList);
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
			MerchantCategoryScopeDO merchantCategoryScope,List<Long> idList) {
		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
		if ( merchantCategoryScope == null || (idList != null && idList.size() == 0)) {
			
			log.error(" param error : MerchantCategoryScopeDO merchantCategoryScope={}",JSON.toJSONString(merchantCategoryScope));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getMerchantCategoryScope(merchantCategoryScope,idList);
			return result;
		} catch (Exception e) {
			log.error("param : MerchantCategoryScopeDO merchantCategoryScope={} error :{}",JSON.toJSONString(merchantCategoryScope),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(
			CategoryQualificationDO categoryQualificationDO,List<Long> idList) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();
		if (categoryQualificationDO == null) {
			log.error(" param error : categoryQualificationDO={}",JSON.toJSONString(categoryQualificationDO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getCategoryQualification(categoryQualificationDO,idList);
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
			Set<Long> idList = new HashSet<Long>();
			for (MerchantQualificationDO mqDO : result.getValue()) {
				idList.add(mqDO.getId());
			}
			if (idList.size() == 0) {
				qualifications.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
				return qualifications;
			}
			QualificationDO qualification = new QualificationDO();
			//qualification.setIdList(idList);
			qualification.setDomainId(merchantQualification.getDomainId());
			//qualification.set
			 qualifications = applyManager.getQualification(qualification,idList);
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
			QualificationDO qualification,Set<Long> idList) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (qualification == null || (idList != null && idList.size() == 0)) {
			log.error("params error:qualification={} ",JSON.toJSONString(qualification));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getQualification(qualification,idList);
			return result;
		} catch (Exception e) {
			log.error("params :qualification={} error:{}",JSON.toJSONString(qualification),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScope(
			BusinessScopeDO businessScope,List<Long> idList) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
		if (businessScope == null || (idList != null && idList.size() == 0)) {
			log.error("params error:businessScope={}",JSON.toJSONString(businessScope));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		
		try {
			result = applyManager.getBusinessScope(businessScope,idList);
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

	@Override
	public MemResult<List<MerchantScopeDO>> getMerchantScope(
			MerchantScopeDO merchantScope) {
		MemResult<List<MerchantScopeDO>> result = new MemResult<List<MerchantScopeDO>>();
		if (merchantScope == null) {
			log.error("param error:merchantScope={}",merchantScope);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getMerchantScope(merchantScope);
			//return result;
		} catch (Exception e) {
			log.error("params :merchantScope={} error:{}",JSON.toJSONString(merchantScope),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}
	@Override
	public MemResult<List<MerchantQualificationDO>> getMerchantQualification(
			MerchantQualificationDO merchantQualification) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		if (merchantQualification == null) {
			log.error("param merchantQualification={}",merchantQualification);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getMerchantQualification(merchantQualification);
			//return result;
		} catch (Exception e) {
			log.error("params :merchantQualification={} error:{}",JSON.toJSONString(merchantQualification),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

}
