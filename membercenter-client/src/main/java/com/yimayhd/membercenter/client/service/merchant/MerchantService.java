package com.yimayhd.membercenter.client.service.merchant;


import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.user.client.domain.UserDO;

import java.util.List;

/**
 * Created by root on 15-11-25.
 */
public interface MerchantService {

    /**
     * 微信注册用户
     * @param merchantVO    MerchantVO
     * @return  BaseResult<UserDO>
     */
    MemResult<UserDO> registerUser(MerchantVO merchantVO);

    /**
     * 根据openId和merChantId查询对应的客户
     * @param merchantVO    MerchantVO
     * @return  BaseResult<UserDO>
     */
    MemResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO);

    /**
     * 根据MerchantId及相关条件查询分页的该商家的客户
     * @param merchantPageQueryVO   MerchantPageQueryVO
     * @return  MemResult<List<UserDO>>
     */
    MemResult<List<UserDO>> findPageUsersByMerchant(MerchantPageQueryVO merchantPageQueryVO);
}
