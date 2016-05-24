package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.ScopeItemCategoryService;
import com.yimayhd.membercenter.manager.ScopeItemCategoryManager;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ScopeItemCategoryServiceImpl implements ScopeItemCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeItemCategoryServiceImpl.class);

    @Autowired
    private ScopeItemCategoryManager scopeItemCategoryManager;

    @Override
    public MemResult<List<ScopeItemCategoryDO>> getScopeItemCategoriesByMerchantScope(List<BusinessScopeDO> businessScopeDOs) {
        if (null == businessScopeDOs || businessScopeDOs.isEmpty()) {
            LOGGER.info("businessScopeDOs not found by businessScopeDOs={}", businessScopeDOs);
            return MemResult.buildFailResult(0, "参数为空", null);
        }
        MemResult<List<ScopeItemCategoryDO>> result = new MemResult<List<ScopeItemCategoryDO>>();
        long[] scopeIds = new long[businessScopeDOs.size()];
        for (int i = 0; i < businessScopeDOs.size(); i++) {
            scopeIds[i] = businessScopeDOs.get(i).getId();
        }
        List<ScopeItemCategoryDO> scopeItemCategoryDOs = scopeItemCategoryManager.getScopeItemCategoryByMerchantScope(scopeIds);
        if (scopeItemCategoryDOs.isEmpty()) {
            LOGGER.info("scopeItemCategory not found by merchantVO={}", scopeItemCategoryDOs);
            return MemResult.buildFailResult(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR.getCode(),
                    MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR.getDesc(), null);
        }
        result.setValue(scopeItemCategoryDOs);
        return result;
    }
}
