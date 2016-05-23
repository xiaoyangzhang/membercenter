package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;

public interface MerchantCategoryScopeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantCategoryScopeDO record);

    int insertSelective(MerchantCategoryScopeDO record);

    MerchantCategoryScopeDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantCategoryScopeDO record);

    int updateByPrimaryKey(MerchantCategoryScopeDO record);
}