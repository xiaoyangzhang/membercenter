package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;

public interface QualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(QualificationDO record);

    int insertSelective(QualificationDO record);

    QualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(QualificationDO record);

    int updateByPrimaryKey(QualificationDO record);
    /**
     * @param qualificationIds 资质id集合
     * @param domainId
     * @return
     */
    List<QualificationDO> selectAllQualifications(@Param("domainId")int domainId);
}