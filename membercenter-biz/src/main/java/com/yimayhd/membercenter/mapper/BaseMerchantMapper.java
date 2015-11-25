package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;

/**
 * Created by root on 15-11-25.
 */
public interface BaseMerchantMapper {

    int insert(BaseMerchantDO baseMerchantDO);

    BaseMerchantDO getById(Long id);

    Long getCount(BaseMerchantDO baseMerchantDO);

    int updateByPrimaryKeySelective(BaseMerchantDO baseMerchantDO);
}
