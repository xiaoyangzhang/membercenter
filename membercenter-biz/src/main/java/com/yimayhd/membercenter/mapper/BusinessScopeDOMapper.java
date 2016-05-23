package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;

public interface BusinessScopeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(BusinessScopeDO record);

    int insertSelective(BusinessScopeDO record);

    BusinessScopeDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(BusinessScopeDO record);

    int updateByPrimaryKey(BusinessScopeDO record);
}