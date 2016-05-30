package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;

public interface MerchantCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantCategoryDO record);

    int insertSelective(MerchantCategoryDO record);

    MerchantCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantCategoryDO record);

    int updateByPrimaryKey(MerchantCategoryDO record);
    
    public List<MerchantCategoryDO> getAllMerchantCategory(@Param("domainId")int domainId);

    MerchantCategoryDO getMerchantCategoriesById(@Param("domainId") int domainId, @Param("id") long id);
}