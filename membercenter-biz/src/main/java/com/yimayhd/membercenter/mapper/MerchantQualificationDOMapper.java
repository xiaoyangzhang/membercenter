package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;

public interface MerchantQualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantQualificationDO record);

    int insertSelective(MerchantQualificationDO record);

    MerchantQualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantQualificationDO record);

    int updateByPrimaryKey(MerchantQualificationDO record);
}