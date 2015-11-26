package com.yimayhd.membercenter.client.service.merchant;

import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;

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

    /**
     * 生成用户的二维码
     * @param merchantVO    MerchantVO
     * @return BaseResult<String>
     */
    BaseResult<String> getTwoDimensionCode(MerchantVO merchantVO);

    /**
     * 根据openId和merChantId查询对应的客户
     * @param merchantVO    MerchantVO
     * @return  BaseResult<UserDO>
     */
    BaseResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO);

    /**
     * 根据二维码获取用户信息
     * @param twoDimensionCode  twoDimensionCode
     * @return  BaseResult<UserDO>
     */
    BaseResult<UserDO> findUserByTwoDimensionCode(String twoDimensionCode);
}
