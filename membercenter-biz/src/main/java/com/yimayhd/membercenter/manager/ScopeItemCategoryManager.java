package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.dao.ScopeItemCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hanlei on 2016/5/23.
 */
public class ScopeItemCategoryManager {

    @Autowired
    private ScopeItemCategoryDao scopeItemCategoryDao;

    public List<ScopeItemCategoryDO> getScopeItemCategoryByMerchantScope(int domainId, long[] scopeIds) {
        return scopeItemCategoryDao.getScopeItemCategoriesByMerchantScope(domainId, scopeIds);
    }

    public List<ScopeItemCategoryDO> getScopeItemCategoriesByCategory(int domainId, long[] categoryIds) {
        return scopeItemCategoryDao.getScopeItemCategoriesByCategory(domainId, categoryIds);
    }
}
