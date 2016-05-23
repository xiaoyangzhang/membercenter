package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;

public interface MerchantCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantCategoryDO record);

    int insertSelective(MerchantCategoryDO record);

    MerchantCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantCategoryDO record);

    int updateByPrimaryKey(MerchantCategoryDO record);
}