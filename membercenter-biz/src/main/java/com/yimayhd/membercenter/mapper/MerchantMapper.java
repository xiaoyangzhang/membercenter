package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.MerchantDO;

/**
 * Created by root on 15-11-25.
 */
public interface MerchantMapper {

    int insert(MerchantDO merchantDO);

    MerchantDO getById(Long id);

    Long getCount(MerchantDO merchantDO);

    int updateByPrimaryKeySelective(MerchantDO merchantDO);
}
