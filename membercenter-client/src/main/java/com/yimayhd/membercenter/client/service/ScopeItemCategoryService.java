package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by hanlei on 2016/5/23.
 */
public interface ScopeItemCategoryService {
    MemResult<List<ScopeItemCategoryDO>> getScopeItemCategoriesByMerchantScope(long[] scopeIds);
}
