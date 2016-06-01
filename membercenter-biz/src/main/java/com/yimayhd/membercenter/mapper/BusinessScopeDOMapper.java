package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;

public interface BusinessScopeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(BusinessScopeDO record);

    int insertSelective(BusinessScopeDO record);

    BusinessScopeDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(BusinessScopeDO record);

    int updateByPrimaryKey(BusinessScopeDO record);
    /**
     * 获取所有是经营范围
     * @param domainId
     * @return
     */
   // public List<BusinessScopeDO> getAllBusinessScope(@Param("domainId") int domainId);
    
   // public List<BusinessScopeDO> getBusinessScopesByScope(@Param("domainId") int domainId,@Param("scopeIds") Long[] scopeIds);
    public List<BusinessScopeDO> getBusinessScopes(@Param("scope") BusinessScopeDO businessScopeDO,@Param("idList")List<Long> idList);
}