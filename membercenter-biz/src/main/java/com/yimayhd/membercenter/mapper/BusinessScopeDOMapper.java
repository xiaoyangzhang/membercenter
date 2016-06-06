package com.yimayhd.membercenter.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;

public interface BusinessScopeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(BusinessScopeDO record);

    int insertSelective(BusinessScopeDO record);

    BusinessScopeDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(BusinessScopeDO record);

    int updateByPrimaryKey(BusinessScopeDO record);
    /**
     * 获取经营范围
     * @param domainId
     * @return
     */
    
    public List<BusinessScopeDO> getBusinessScopes(@Param("scope") BusinessScopeDO businessScope,@Param("idList")Set<Long> idList);
}