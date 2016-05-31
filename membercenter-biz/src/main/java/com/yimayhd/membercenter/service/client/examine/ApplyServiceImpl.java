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
	public MemResult<List<BusinessScopeDO>> getBusinessScopeBySellerId(long sellerId,int domainId) {
		MemResult<List<BusinessScopeDO>> scopes = new MemResult<List<BusinessScopeDO>>();
		if (sellerId <= 0 || domainId <= 0) {
			log.error("params error:sellerId={} domainId={}",sellerId,domainId);
			scopes.setReturnCode(MemberReturnCode.PARAMTER_ERROR);;
			return scopes;
		}
		try {
			  MemResult<List<MerchantScopeDO>> result = applyManager.getBusinessScopeBySellerId(sellerId, domainId);
			  if (result == null || !result.isSuccess()) {
				scopes.setReturnCode(MemberReturnCode.MERCHANT_SCOPE_FAILED);
				return scopes;
			}
			List<Long> idList = new ArrayList<Long>();
			for (MerchantScopeDO msDO : result.getValue()) {
				idList.add(msDO.getId());
			}
			scopes = applyManager.getBusinessScopesByIds(domainId, idList);
			return scopes;
		} catch (Exception e) {
			log.error("param :sellerId={} domainId={} error :{}",sellerId,domainId,e);
			scopes.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return scopes;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getAllBusinessScope(int domainId) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
		if (domainId <= 0) {
			log.error(" param error : domainId={}",domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getAllBusinessScope(domainId);
			return result;
		} catch (Exception e) {
			log.error("param : domainId={} error :{}",domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<QualificationDO>> getAllQualification(int domainId) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (domainId <= 0) {
			log.error(" param error : domainId={}",domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getAllQualification(domainId);
			return result;
		} catch (Exception e) {
			log.error("param : domainId={} error :{}",domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<MerchantCategoryScopeDO>> getBusinessScopeByMerchantCategoryId(
			long merchantCategoryId, int domainId) {
		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
		if (domainId <= 0 || merchantCategoryId <= 0) {
			log.error(" param error : domainId={} merchantCategoryId={}",domainId,merchantCategoryId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getScopeByMerchantCategoryId(merchantCategoryId, domainId);
			return result;
		} catch (Exception e) {
			log.error("param : domainId={} merchantCategoryId={} error :{}",domainId,merchantCategoryId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<CategoryQualificationDO>> getQualificationByMerchantCategoryId(
			long merchantCategoryId, int domainId) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();
		if (domainId <= 0 || merchantCategoryId <= 0) {
			log.error(" param error : domainId={} merchantCategoryId={}",domainId,merchantCategoryId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getQualificationByMerchantCategoryId(merchantCategoryId, domainId);
			return result;
		} catch (Exception e) {
			log.error("param : domainId={} merchantCategoryId={} error :{}",domainId,merchantCategoryId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<QualificationDO>> getMerchantQualificationBySellerId(
			long sellerId, int domainId) {
		MemResult<List<QualificationDO>> qualifications = new MemResult<List<QualificationDO>>();
		if (domainId <= 0 || sellerId <= 0) {
			log.error(" param error : domainId={} sellerId={}",domainId,sellerId);
			qualifications.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return qualifications;
		}
		try {
			 MemResult<List<MerchantQualificationDO>> result = applyManager.getMerchantQualificationBySellerId(sellerId, domainId);
			if (!result.isSuccess()) {
				qualifications.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
				return qualifications;
			}
			List<Long> idList = new ArrayList<Long>();
			for (MerchantQualificationDO mqDO : result.getValue()) {
				idList.add(mqDO.getId());
			}
			 qualifications = applyManager.getQualificationByIds(idList, domainId);
			return qualifications;
		} catch (Exception e) {
			log.error("param : domainId={} sellerId={} error :{}",domainId,sellerId,e);
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
	public MemResult<List<QualificationDO>> getQualificationByIds(
			List<Long> idList, int domainId) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (idList == null || domainId <= 0) {
			log.error("params error:idList={} domainId={}",idList,domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getQualificationByIds(idList, domainId);
			return result;
		} catch (Exception e) {
			log.error("params :idList={} domainId={} error:{}",idList,domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScopeByIds(
			List<Long> idList, int domainId) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
		if (idList == null || domainId <= 0) {
			log.error("params error:idList={} domainId={}",idList,domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		
		try {
			result = applyManager.getBusinessScopesByIds(domainId, idList);
			return result;
		} catch (Exception e) {
			log.error("params :idList={} domainId={} error:{}",idList,domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, List<Long> ids) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
		if (domainId <= 0 || ids == null || ids.size() == 0) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
		}
		result = applyManager.getBusinessScopesByIds(domainId, ids);
		return result;
	}

	public MemResult<List<MerchantCategoryDO>> getAllMerchantCategory(
			int domainId) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
		if (domainId <= 0 ) {
			log.error("param error:domainId={}",domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getAllMerchantCategory(domainId);
			return result;
		} catch (Exception e) {
			log.error("params : domainId={} error:{}",domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<MerchantCategoryDO> getMerchantCategory(int domainId, long id) {
		return applyManager.getMerchantCategory(id,domainId);
	}

}
