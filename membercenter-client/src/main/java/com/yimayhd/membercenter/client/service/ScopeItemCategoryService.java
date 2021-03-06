package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by hanlei on 2016/5/23.
 */
public interface ScopeItemCategoryService {
	@Deprecated
	MemResult<List<ScopeItemCategoryDO>> findScopeItemCategoriesByMerchantScope(int domainId, long[] scopeIds);

	MemResult<List<ScopeItemCategoryDO>> findScopeItemCategories(int domainId, List<ScopeItemCategoryDO> scopeItemCategoryDOs);

	MemResult<List<ScopeItemCategoryDO>> findScopeItemCategoriesByCategory(int domainId, long[] categoryIds);
}
