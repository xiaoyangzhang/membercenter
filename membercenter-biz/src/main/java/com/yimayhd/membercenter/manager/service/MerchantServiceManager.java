package com.yimayhd.membercenter.manager.service;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;

/**
 * Created by root on 15-11-25.
 */
public interface MerchantServiceManager {

    BaseMerchantDO findBaseMerchantDOById(Long id);

}
