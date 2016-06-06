package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;

public interface MerchantQualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantQualificationDO record);

    int insertSelective(MerchantQualificationDO record);

    MerchantQualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantQualificationDO record);

    int updateByPrimaryKey(MerchantQualificationDO record);
    /**
     * 
    * created by zhangxy
    * @date 2016年5月25日
    * @Title: getMerchantQualification
    * @Description: 获取商家与资质的关联对象的集合
    * @param  merchantQualification
    
    * @return List<MerchantQualificationDO>    返回类型 
    * @throws
     */
    public List<MerchantQualificationDO> getMerchantQualification(@Param("merchantQualification")QualificationQueryDTO queryDTO);
    /**
     * 
    * created by zhangxy
    * @date 2016年6月5日
    * @Title: updateStatusBatch 
    * @Description: 批量更新状态
    * @param @param queryDTOList
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws
     */
    public int updateStatusBatch(@Param("list")List<MerchantQualificationDO> qualificationDOList);
}