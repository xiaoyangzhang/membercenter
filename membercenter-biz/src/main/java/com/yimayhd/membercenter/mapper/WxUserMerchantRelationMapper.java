package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.MerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;

import java.util.List;

/**
 * Created by root on 15-11-25.
 */
public interface WxUserMerchantRelationMapper {

    Long insert(WxUserMerchantRelationDO wxUserMerchantRelationDO);

    WxUserMerchantRelationDO getById(Long id);

    Long getCount(WxUserMerchantRelationDO wxUserMerchantRelationDO);

    int updateByPrimaryKeySelective(WxUserMerchantRelationDO wxUserMerchantRelationDO);

    /**
     * 查询满足条件的WxUserMerchantRelationDO
     * @param wxUserMerchantRelationDO  WxUserMerchantRelationDO
     * @return    List<WxUserMerchantRelationDO>
     */
    List<WxUserMerchantRelationDO> findByCondition(WxUserMerchantRelationDO wxUserMerchantRelationDO);
}
