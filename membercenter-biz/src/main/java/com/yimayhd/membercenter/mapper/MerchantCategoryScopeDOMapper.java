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
    * @Title: getMerchantCategoryScope 
    * @Description: 获取经营范围
    * @param  merchantCategoryScopeDO
    * @param     设定文件 
    * @return List<MerchantCategoryScopeDO>    返回类型 
    * @throws
     */
    public List<MerchantCategoryScopeDO> getMerchantCategoryScope(@Param("merchantCategoryScope")MerchantCategoryScopeDO merchantCategoryScopeDO);

    /**
     * 根据scopeIds查询category集合
     * @param scopeIds
     * @param domainId
     * @return
     */
   // List<MerchantCategoryScopeDO> getMerchantCatergoryScopesByScopeIds(@Param("scopeIds") long[] scopeIds, @Param("domainId") int domainId);
}