package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.query.MerchantItemCategoryQueryDTO;

public interface MerchantItemCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantItemCategoryDO record);

    int insertSelective(MerchantItemCategoryDO record);

    MerchantItemCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantItemCategoryDO record);

    int updateByPrimaryKey(MerchantItemCategoryDO record);
    
    List<MerchantItemCategoryDO> selectByMerchant(@Param("domainId") int domainId, @Param("sellerId") long sellerId);
    @Deprecated
    List<MerchantItemCategoryDO> selectByCategoryIdAndSellerId(@Param("domainId") int domainId, @Param("itemCategoryId") long categoryId, @Param("sellerId") long sellerId);
    MerchantItemCategoryDO selectObjByCategoryIdAndSellerId(@Param("domainId") int domainId, @Param("itemCategoryId") long categoryId, @Param("sellerId") long sellerId);
    
    //MerchantItemCategoryDO selectMerchantItemCategory(MerchantItemCategoryQueryDTO queryDTO);
}