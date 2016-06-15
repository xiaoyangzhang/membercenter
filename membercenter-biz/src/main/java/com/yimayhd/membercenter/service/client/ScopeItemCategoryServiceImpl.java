package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.ScopeItemCategoryService;
import com.yimayhd.membercenter.manager.ScopeItemCategoryManager;

/**
 * Created by hanlei on 2016/5/23.
 */
public class ScopeItemCategoryServiceImpl implements ScopeItemCategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScopeItemCategoryServiceImpl.class);

	@Autowired
	private ScopeItemCategoryManager scopeItemCategoryManager;

	@Deprecated
	@Override
	public MemResult<List<ScopeItemCategoryDO>> findScopeItemCategoriesByMerchantScope(int domainId, long[] scopeIds) {
		MemResult<List<ScopeItemCategoryDO>> scopeItemCategoryResult = new MemResult<>();
		if (null == scopeIds || scopeIds.length <= 0) {
			LOGGER.error("businessScopeDOs not found by scopeIds={}", scopeIds);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return scopeItemCategoryResult;
		}
		MemResult<List<ScopeItemCategoryDO>> result = new MemResult<List<ScopeItemCategoryDO>>();
		List<ScopeItemCategoryDO> scopeItemCategoryDOs = scopeItemCategoryManager
				.getScopeItemCategoryByMerchantScope(domainId, scopeIds);
		if (scopeItemCategoryDOs.isEmpty()) {
			LOGGER.error("businessScopeDOs not found by scopeIds={}", scopeIds);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
			return scopeItemCategoryResult;
		}
		result.setValue(scopeItemCategoryDOs);
		return result;
	}

	@Override
	public MemResult<List<ScopeItemCategoryDO>> findScopeItemCategories(int domainId, List<ScopeItemCategoryDO> scopeItemCategoryDOs) {

		MemResult<List<ScopeItemCategoryDO>> scopeItemCategoryResult = new MemResult<>();
		if (null == scopeItemCategoryDOs || scopeItemCategoryDOs.isEmpty()) {
			LOGGER.error("scopeItemCategoryDOs not found by scopeIds={}", scopeItemCategoryDOs);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return scopeItemCategoryResult;
		}
		MemResult<List<ScopeItemCategoryDO>> result = new MemResult<List<ScopeItemCategoryDO>>();
		List<ScopeItemCategoryDO> scopeItemCategoryDOList = scopeItemCategoryManager.getScopeItemCategories(scopeItemCategoryDOs);
		if (scopeItemCategoryDOList.isEmpty()) {
			LOGGER.error("businessScopeDOs not found by scopeIds={}", scopeItemCategoryDOList);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
			return scopeItemCategoryResult;
		}
		result.setValue(scopeItemCategoryDOList);
		return result;
	}

	@Override
	public MemResult<List<ScopeItemCategoryDO>> findScopeItemCategoriesByCategory(int domainId, long[] categoryIds) {
		MemResult<List<ScopeItemCategoryDO>> scopeItemCategoryResult = new MemResult<>();
		if(domainId <= 0 || null == categoryIds || categoryIds.length == 0) {
			LOGGER.error("businessScopeDOs not found by categoryIds={}", categoryIds);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return scopeItemCategoryResult;
		}
		List<ScopeItemCategoryDO> scopeItemCategoryDOs = scopeItemCategoryManager.getScopeItemCategoriesByCategory(domainId, categoryIds);
		if (scopeItemCategoryDOs.isEmpty()) {
			LOGGER.error("businessScopeDOs not found by categoryIds={}", categoryIds);
			scopeItemCategoryResult.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
			return scopeItemCategoryResult;
		}
		scopeItemCategoryResult.setValue(scopeItemCategoryDOs);
		return scopeItemCategoryResult;
	}
}
