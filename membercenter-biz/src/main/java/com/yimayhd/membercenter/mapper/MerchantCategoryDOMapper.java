package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;

public interface MerchantCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantCategoryDO record);

    int insertSelective(MerchantCategoryDO record);

    MerchantCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantCategoryDO record);

    int updateByPrimaryKey(MerchantCategoryDO record);
    
   //public List<MerchantCategoryDO> getAllMerchantCategory(@Param("domainId")int domainId);

    List<MerchantCategoryDO> getMerchantCategory(@Param("category") MerchantCategoryDO	 merchantCategory);
}