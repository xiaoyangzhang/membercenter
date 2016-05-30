package com.yimayhd.membercenter.service.client.examine;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.ApplyService;
import com.yimayhd.membercenter.manager.ApplyManager;

public class ApplyServiceImpl implements ApplyService {
	
	private static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

	@Autowired
	private ApplyManager applyManager;
	@Override
	public MemResult<List<MerchantScopeDO>> getMerchantScopeBySellerId(long sellerId,int domainId) {
		MemResult<List<MerchantScopeDO>> result = new MemResult<List<MerchantScopeDO>>();
		if (sellerId <= 0 || domainId <= 0) {
			log.error("params error:sellerId={} domainId={}",sellerId,domainId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);;
			return result;
		}
		try {
			 result = applyManager.getBusinessScopeBySellerId(sellerId, domainId);
			
			return result;
		} catch (Exception e) {
			log.error("param :sellerId={} domainId={} error :{}",sellerId,domainId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
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
	public MemResult<List<MerchantQualificationDO>> getMerchantQualificationBySellerId(
			long sellerId, int domainId) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		if (domainId <= 0 || sellerId <= 0) {
			log.error(" param error : domainId={} sellerId={}",domainId,sellerId);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.getMerchantQualificationBySellerId(sellerId, domainId);
			return result;
		} catch (Exception e) {
			log.error("param : domainId={} sellerId={} error :{}",domainId,sellerId,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto,MerchantQualificationDO mqDO,MerchantScopeDO msDO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (dto == null || mqDO == null || msDO == null) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = applyManager.submitExamineInfo(dto, mqDO, msDO);
			return result;
		} catch (Exception e) {
			log.error("params : ExamineInfoDTO={}  MerchantQualificationDO={} MerchantScopeDO={} error :{}",dto,mqDO,msDO,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, long[] ids) {
		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
		if (domainId <= 0 || ids == null || ids.length == 0) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
		}
		result = applyManager.getBusinessScopesByIds(domainId, ids);
		return result;
	}

	@Override
	public MemResult<List<MerchantCategoryDO>> getMerchantCategoriesBySellerId(int domainId, long sellerId) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<>();
		if (domainId <= 0 || sellerId <= 0) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
		}
		result = applyManager.getMerchantCategoriesByIds(domainId, sellerId);
		return result;
	}

}
