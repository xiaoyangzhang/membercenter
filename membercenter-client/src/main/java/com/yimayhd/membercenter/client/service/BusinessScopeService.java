package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by hanlei on 2016/5/24.
 */
public interface BusinessScopeService {
	MemResult<List<BusinessScopeDO>> findBusinessScopesByScope(int domainId, long[] scopeIds);
}
