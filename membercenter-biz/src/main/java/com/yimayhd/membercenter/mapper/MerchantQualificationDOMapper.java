package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;

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
    public List<MerchantQualificationDO> getMerchantQualification(@Param("merchantQualification") MerchantQualificationDO merchantQualification);
}