package com.yimayhd.membercenter.service.merchant.impl;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.service.merchant.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.manager.service.MerchantServiceManager;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import net.pocrd.entity.ApiReturnCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by root on 15-11-25.
 */
public class MerchantServiceImpl implements MerchantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Resource
    private UserService userService;

    @Resource
    private MerchantServiceManager merchantServiceManager;

    @Override
    public BaseResult<UserDO> rigisterUser(MerchantVO merchantVO) {
        LOGGER.info("rigisterUser merchantVO= {}", merchantVO);

        if (checkParam(merchantVO)) {
            return BaseResult.buildFailResult(ApiReturnCode.PARAMETER_ERROR, null);
        }

        UserDO userDO = new UserDO();
        userDO.setMobile(merchantVO.getMobile());
        com.yimayhd.user.client.result.BaseResult<UserDO> result = userService.createUserAndPutCache(userDO);

        if (ApiReturnCode._C_SUCCESS != Integer.valueOf(result.getErrorCode())) {
            return BaseResult.buildFailResult(result.getErrorCode(), result.getResultMsg(), null);
        }

        UserDO createUserDO = result.getValue();
        LOGGER.info("createUserDO.getId={}", createUserDO.getId());

        BaseMerchantDO baseMerchantDO = merchantServiceManager.findBaseMerchantDOById(merchantVO.getMerchantId());

        
        return null;
    }

    @Override
    public BaseResult<String> getTwoDimensionCode(MerchantVO merchantVO) {
        return null;
    }

    @Override
    public BaseResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO) {
        return null;
    }

    @Override
    public BaseResult<UserDO> findUserByTwoDimensionCode(String twoDimensionCode) {
        return null;
    }

    private boolean checkParam(MerchantVO merchantVO) {
        return StringUtils.isBlank(merchantVO.getOpenId()) ||
                StringUtils.isBlank(merchantVO.getMobile()) || null == merchantVO.getMerchantId();
    }
}
