package com.yimayhd.membercenter.service.client;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BusinessScopeService;
import com.yimayhd.membercenter.manager.BusinessScopeManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeServiceImpl implements BusinessScopeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeItemCategoryServiceImpl.class);

    @Autowired
    private BusinessScopeManager businessScopeManager;

    @Override
    public MemResult<List<BusinessScopeDO>> findBusinessScopesByScope(BusinessScopeQueryDTO queryDTO) {
        MemResult<List<BusinessScopeDO>> result = new MemResult<>();
        if (queryDTO == null) {
            LOGGER.info("businessScopeDOs not found by BusinessScopeQueryDTO={}", JSON.toJSONString(queryDTO));
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return result;
        }
        List<BusinessScopeDO> businessScopeDOs = businessScopeManager.getBusinessScopesByScope(queryDTO);
        result.setValue(businessScopeDOs);
        return result;
    }

	@Override
	public MemResult<List<MerchantCategoryScopeDO>> getMerchantCategoryScope(
			BusinessScopeQueryDTO queryDTO) {
		MemResult<List<MerchantCategoryScopeDO>> result = new MemResult<List<MerchantCategoryScopeDO>>();
		if (queryDTO == null || queryDTO.getDomainId() <= 0) {
			LOGGER.error("params error:BusinessScopeQueryDTO={}",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			List<MerchantCategoryScopeDO> merchantCategoryScopes = businessScopeManager.getMerchantCategoryScope(queryDTO);
			result.setValue(merchantCategoryScopes);
		} catch (Exception e) {
			LOGGER.error("params :BusinessScopeQueryDTO={} error:{} ",queryDTO,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<List<MerchantScopeDO>> getMerchantScope(
			BusinessScopeQueryDTO queryDTO) {
		MemResult<List<MerchantScopeDO>> result = new MemResult<List<MerchantScopeDO>>();
		if (queryDTO == null || queryDTO.getDomainId() <= 0) {
			LOGGER.error("params error:BusinessScopeQueryDTO={}",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			List<MerchantScopeDO> merchantScopes = businessScopeManager.getMerchantScope(queryDTO);
			result.setValue(merchantScopes);
		} catch (Exception e) {
			LOGGER.error("params :BusinessScopeQueryDTO={} error:{} ",queryDTO,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<Boolean> updateMerchantScopeStatus(
			BusinessScopeQueryDTO queryDTO) {
		
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (queryDTO == null || queryDTO.getDomainId() <= 0 ) {
			LOGGER.error("params error:BusinessScopeQueryDTO={}",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
//			List<MerchantScopeDO> merchantScopes = businessScopeManager.getMerchantScope(queryDTO);
//			result.setValue(merchantScopes);
			result = businessScopeManager.updateMerchantScopeStatus(queryDTO);
		} catch (Exception e) {
			LOGGER.error("params :BusinessScopeQueryDTO={} error:{} ",queryDTO,e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<Integer> updateStatusBatch(List<BusinessScopeQueryDTO> queryDTOs) {
			MemResult<Integer> result = new MemResult<Integer>();
			if (queryDTOs == null /*|| qualificationQueryDTOs.getDomainId() <= 0 */) {
				LOGGER.error(" param error : BusinessScopeQueryDTO={}",JSON.toJSONString(queryDTOs));
				result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
				return result;
			}
			try {
				result = businessScopeManager.updateStatusBatch(queryDTOs);
			} catch (Exception e) {
				LOGGER.error(" param error : BusinessScopeQueryDTO={} error:{}",JSON.toJSONString(queryDTOs),e);
				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			}
			return result;
		}
	

}
