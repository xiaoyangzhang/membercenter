package com.yimayhd.membercenter.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;

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
    * @Title: getBusinessScope 
    * @Description:获取经营范围
    * @param merchantScope
    * @return List<MerchantScopeDO>    返回类型 
    * @throws
     */
    public List<MerchantScopeDO> getMerchantScope(@Param("merchantScope")MerchantScopeDO businessScopeQueryDTO);
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
    public int updateStatusBatch(@Param("list")List<MerchantScopeDO> scopeDOs);
}