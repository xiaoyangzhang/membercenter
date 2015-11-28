package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.mapper.BaseMerchantMapper;
import com.yimayhd.membercenter.mapper.MerchantMapper;
import com.yimayhd.membercenter.mapper.WxUserMerchantRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 15-11-25.
 */
public class MerchantServiceManager {

    @Autowired
    private BaseMerchantMapper baseMerchantMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private WxUserMerchantRelationMapper wxUserMerchantRelationMapper;


    public BaseMerchantDO findBaseMerchantDOById(Long id) {
        return baseMerchantMapper.getById(id);
    }

    public Long saveUserMerchantRelation(WxUserMerchantRelationDO wxUserMerchantRelationDO) {
        return wxUserMerchantRelationMapper.insert(wxUserMerchantRelationDO);
    }
}
