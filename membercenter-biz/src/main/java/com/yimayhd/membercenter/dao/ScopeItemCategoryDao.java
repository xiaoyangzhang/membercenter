package com.yimayhd.membercenter.dao;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.mapper.ScopeItemCategoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ScopeItemCategoryDao {
    @Autowired
    private ScopeItemCategoryDOMapper scopeItemCategoryDOMapper;

    public List<ScopeItemCategoryDO> getScopeItemCategoriesByMerchantScope(int domainId, long[] scopeIds) {
        return scopeItemCategoryDOMapper.selectByMerchantScope(domainId, scopeIds);
    }

    public List<ScopeItemCategoryDO> getScopeItemCategoriesByCategory(int domainId, long[] categoryIds) {
        return scopeItemCategoryDOMapper.selectByCategory(domainId, categoryIds);
    }

    public List<ScopeItemCategoryDO> getScopeItemCategories(List<ScopeItemCategoryDO> scopeItemCategoryDOs) {
        List<ScopeItemCategoryDO> result = new ArrayList<>();
        for (ScopeItemCategoryDO scopeItemCategoryDO : scopeItemCategoryDOs) {
            result.addAll(scopeItemCategoryDOMapper.selectByScopeIdAndMerchantCategoryId(scopeItemCategoryDO.getDomainId(), scopeItemCategoryDO.getBusinessScopeId(), scopeItemCategoryDO.getMerchantCategoryId()));
        }
        return result;
    }
}
