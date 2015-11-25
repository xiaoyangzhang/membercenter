package com.yimayhd.membercenter.client.service.merchant;

import com.yimayhd.membercenter.client.result.BaseResult;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.user.client.domain.UserDO;

/**
 * Created by root on 15-11-25.
 */
public interface MerchantService {

    /**
     * 微信注册用户
     * @param merchantVO    MerchantVO
     * @return  BaseResult<UserDO>
     */
    BaseResult<UserDO> rigisterUser(MerchantVO merchantVO);
}
