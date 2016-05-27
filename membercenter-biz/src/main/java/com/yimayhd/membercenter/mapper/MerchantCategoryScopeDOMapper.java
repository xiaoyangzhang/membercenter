package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;

public interface MerchantCategoryScopeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MerchantCategoryScopeDO record);

    int insertSelective(MerchantCategoryScopeDO record);

    MerchantCategoryScopeDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(MerchantCategoryScopeDO record);

    int updateByPrimaryKey(MerchantCategoryScopeDO record);
    /**
     * 
    * created by zhangxy
    * @date 2016年5月25日
    * @Title: getMerchantCategoryScopeByMerchantCategoryId 
    * @Description: 根据商家类目id获取经营范围
    * @param  merchantCategoryId
    * @param  domainId
    * @param     设定文件 
    * @return List<MerchantCategoryScopeDO>    返回类型 
    * @throws
     */
    public List<MerchantCategoryScopeDO> getMerchantCategoryScopeByMerchantCategoryId(@Param("merchantCategoryId")long merchantCategoryId,@Param("domainId")int domainId);
}