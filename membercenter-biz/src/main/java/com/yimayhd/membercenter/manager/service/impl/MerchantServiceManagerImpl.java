package com.yimayhd.membercenter.manager.service.impl;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.manager.service.MerchantServiceManager;
import com.yimayhd.membercenter.mapper.BaseMerchantMapper;
import com.yimayhd.membercenter.mapper.MerchantMapper;
import com.yimayhd.membercenter.mapper.WxUserMerchantRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 15-11-25.
 */
public class MerchantServiceManagerImpl implements MerchantServiceManager {

    @Autowired
    private BaseMerchantMapper baseMerchantMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private WxUserMerchantRelationMapper wxUserMerchantRelationMapper;


    @Override
    public BaseMerchantDO findBaseMerchantDOById(Long id) {
        return baseMerchantMapper.getById(id);
    }
}
