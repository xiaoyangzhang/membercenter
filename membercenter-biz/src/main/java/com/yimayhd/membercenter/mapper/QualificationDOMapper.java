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
    /**
     * 
    * created by zhangxy
    * @date 2016年5月28日
    * @Title: getQualificationByIds 
    * @Description: 根据id集合获取资质
    * @param @param idList
    * @param @return    设定文件 
    * @return List<QualificationDO>    返回类型 
    * @throws
     */
    
    List<QualificationDO> getQualificationByIds(@Param("list")List<Long> idList,@Param("domainId") int domainId);
}