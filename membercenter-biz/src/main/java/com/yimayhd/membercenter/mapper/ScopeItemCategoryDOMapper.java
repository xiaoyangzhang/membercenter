package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.ScopeItemCategoryDO;

public interface ScopeItemCategoryDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(ScopeItemCategoryDO record);

    int insertSelective(ScopeItemCategoryDO record);

    ScopeItemCategoryDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(ScopeItemCategoryDO record);

    int updateByPrimaryKey(ScopeItemCategoryDO record);

    List<ScopeItemCategoryDO> selectByMerchantScope(@Param("domainId") int domainId,@Param("scopeIds") long[] scopeIds);
}