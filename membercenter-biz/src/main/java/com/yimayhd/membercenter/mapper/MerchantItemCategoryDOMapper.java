package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;

public interface MerchantItemCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantItemCategoryDO record);

    int insertSelective(MerchantItemCategoryDO record);

    MerchantItemCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantItemCategoryDO record);

    int updateByPrimaryKey(MerchantItemCategoryDO record);
}