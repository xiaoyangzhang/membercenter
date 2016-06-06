package com.yimayhd.membercenter.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;

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
   // List<QualificationDO> selectAllQualifications(@Param("domainId")int domainId);
    /**
     * 
    * created by zhangxy
    * @date 2016年5月28日
    * @Title: getQualification
    * @Description: 获取资质
    * @param qualification
    * @return List<QualificationDO>    返回类型 
    * @throws
     */
    
    List<QualificationDO> getQualification(@Param("qualification")QualificationDO queryDTO,@Param("idList")Set<Long> idList);
}