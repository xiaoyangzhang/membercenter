package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;

public interface QualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(QualificationDO record);

    int insertSelective(QualificationDO record);

    QualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(QualificationDO record);

    int updateByPrimaryKey(QualificationDO record);
}