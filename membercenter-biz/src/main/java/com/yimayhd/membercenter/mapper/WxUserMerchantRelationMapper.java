package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.MerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;

/**
 * Created by root on 15-11-25.
 */
public interface WxUserMerchantRelationMapper {
    int insert(WxUserMerchantRelationDO wxUserMerchantRelationDO);

    WxUserMerchantRelationDO getById(Long id);

    Long getCount(WxUserMerchantRelationDO wxUserMerchantRelationDO);

    int updateByPrimaryKeySelective(WxUserMerchantRelationDO wxUserMerchantRelationDO);
}
