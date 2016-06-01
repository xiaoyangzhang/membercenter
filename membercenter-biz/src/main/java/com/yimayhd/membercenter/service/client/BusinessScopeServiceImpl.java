package com.yimayhd.membercenter.service.client;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BusinessScopeService;
import com.yimayhd.membercenter.manager.BusinessScopeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeServiceImpl implements BusinessScopeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeItemCategoryServiceImpl.class);

    @Autowired
    private BusinessScopeManager businessScopeManager;

    @Override
    public MemResult<List<BusinessScopeDO>> findBusinessScopesByScope(BusinessScopeDO businessScope, List<Long> idList) {
        MemResult<List<BusinessScopeDO>> result = new MemResult<>();
        if (businessScope == null) {
            LOGGER.info("businessScopeDOs not found by businessScope={}", JSON.toJSONString(businessScope));
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return result;
        }
        List<BusinessScopeDO> businessScopeDOs = businessScopeManager.getBusinessScopesByScope(businessScope, idList);
        result.setValue(businessScopeDOs);
        return result;
    }

}
