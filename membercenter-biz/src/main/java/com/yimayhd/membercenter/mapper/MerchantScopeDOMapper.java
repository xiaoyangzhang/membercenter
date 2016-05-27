package com.yimayhd.membercenter.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;

public interface MerchantScopeDOMapper {

    int deleteByPrimaryKey(long id);

    int insert(MerchantScopeDO record);

    int insertSelective(MerchantScopeDO record);


    MerchantScopeDO selectByPrimaryKey(long id);



    int updateByPrimaryKeySelective(MerchantScopeDO record);

    int updateByPrimaryKey(MerchantScopeDO record);
    /**
     * 
    * created by zhangxy
    * @date 2016年5月25日
    * @Title: getBusinessScopeBySellerId 
    * @Description: 根据商家id获取经营范围
    * @param @param sellerId
    * @param @param domainId
    * @param @return    设定文件 
    * @return List<MerchantScopeDO>    返回类型 
    * @throws
     */
    public List<MerchantScopeDO> getBusinessScopeBySellerId(@Param("sellerId")long sellerId,@Param("domainId")int domainId);
}