package com.yimayhd.membercenter.client.service.merchant;

import com.yimayhd.membercenter.client.result.MemResult;
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
    MemResult<UserDO> rigisterUser(MerchantVO merchantVO);
}
