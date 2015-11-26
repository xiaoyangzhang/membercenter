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
    MemResult<UserDO> registerUser(MerchantVO merchantVO);

    /**
     * 生成用户的二维码
     * @param merchantVO    MerchantVO
     * @return BaseResult<String>
     */
    MemResult<String> getTwoDimensionCode(MerchantVO merchantVO);

    /**
     * 根据openId和merChantId查询对应的客户
     * @param merchantVO    MerchantVO
     * @return  BaseResult<UserDO>
     */
    MemResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO);

    /**
     * 根据二维码获取用户信息
     * @param twoDimensionCode  twoDimensionCode
     * @return  BaseResult<UserDO>
     */
    MemResult<UserDO> findUserByTwoDimensionCode(String twoDimensionCode);
}
