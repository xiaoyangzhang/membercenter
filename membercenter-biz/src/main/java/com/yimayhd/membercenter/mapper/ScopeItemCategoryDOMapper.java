package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScopeItemCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(ScopeItemCategoryDO record);

    int insertSelective(ScopeItemCategoryDO record);

    ScopeItemCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(ScopeItemCategoryDO record);

    int updateByPrimaryKey(ScopeItemCategoryDO record);

    List<ScopeItemCategoryDO> selectByMerchantScope(@Param("domainId") int domainId, @Param("scopeIds") long[] scopeIds);

    List<ScopeItemCategoryDO> selectByCategory(@Param("domainId") int domainId, @Param("categoryIds") long[] categoryIds);

    List<ScopeItemCategoryDO> selectByScopeIdAndMerchantCategoryId(@Param("domainId") int domainId, @Param("businessScopeId") long businessScopeId, @Param("merchantCategoryId") long merchantCateogryId);
}