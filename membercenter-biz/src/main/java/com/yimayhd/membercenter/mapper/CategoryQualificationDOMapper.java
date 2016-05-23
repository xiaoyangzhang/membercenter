package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;

public interface CategoryQualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(CategoryQualificationDO record);

    int insertSelective(CategoryQualificationDO record);

    CategoryQualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(CategoryQualificationDO record);

    int updateByPrimaryKey(CategoryQualificationDO record);
}