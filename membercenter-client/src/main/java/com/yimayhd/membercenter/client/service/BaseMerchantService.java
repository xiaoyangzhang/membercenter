package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * 商户基本信息管理类
 * Created by Administrator on 2015/12/1.
 */
public interface BaseMerchantService {

    /**
     * 根据用户id查询上回基本信息
     * @param userId
     * @return
     */
    public MemResult<BaseMerchantDO>  getMerchantByUserId(long userId);


}
