package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BusinessScopeService;
import com.yimayhd.membercenter.manager.BusinessScopeManager;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeServiceImpl implements BusinessScopeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeItemCategoryServiceImpl.class);

	@Autowired
	private BusinessScopeManager businessScopeManager;

	@Override
	public MemResult<List<BusinessScopeDO>> findBusinessScopesByScope(int domainId, long[] scopeIds) {
        if (null == scopeIds || scopeIds.length <= 0) {
            LOGGER.info("businessScopeDOs not found by scopeIds={}", scopeIds);
            return MemResult.buildFailResult(0, "参数为空", null);
        }
		List<BusinessScopeDO> businessScopeDOs = businessScopeManager.getBusinessScopesByScope(domainId, scopeIds);
		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
		result.setValue(businessScopeDOs);
		return result;
	}

}
