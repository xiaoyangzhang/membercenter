package com.yimayhd.membercenter.service.merchant.impl;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.merchant.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.user.client.domain.UserDO;
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

    @Override
    public MemResult<UserDO> rigisterUser(MerchantVO merchantVO) {
    	MemResult<UserDO> result = new MemResult<UserDO>() ;
        LOGGER.info("rigisterUser merchantVO= {}", merchantVO);

        if (checkParam(merchantVO)) {
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return result ;
        }

        UserDO userDO = new UserDO();
        userDO.setMobile(merchantVO.getMobile());
        com.yimayhd.user.client.result.BaseResult<UserDO> createUserResult = userService.createUserAndPutCache(userDO);

        if (ApiReturnCode._C_SUCCESS != Integer.valueOf(createUserResult.getErrorCode())) {
//            return MemResult.buildFailResult(createUserResult.getErrorCode(), createUserResult.getResultMsg(), null);
        	//FIXME 侯冬辉 不要 返回其他系统的errorcode和errorMsg，每个系统应该只返回自己的error信息，
            return result ;
        }

        UserDO createUserDO = createUserResult.getValue();
        LOGGER.info("createUserDO.getId={}", createUserDO.getId());



        return null;
    }

    private boolean checkParam(MerchantVO merchantVO) {
        return StringUtils.isBlank(merchantVO.getOpenId()) ||
                StringUtils.isBlank(merchantVO.getMobile()) || null == merchantVO.getMerchantId();
    }
}
