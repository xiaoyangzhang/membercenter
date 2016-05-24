package com.yimayhd.membercenter.dao;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.mapper.ScopeItemCategoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ScopeItemCategoryDao {
    @Autowired
    private ScopeItemCategoryDOMapper scopeItemCategoryDOMapper;

    public List<ScopeItemCategoryDO> getScopeItemCategoriesByMerchantScope(long[] scopeIds) {
        return scopeItemCategoryDOMapper.selectByMerchantScope(scopeIds);
    }
}
