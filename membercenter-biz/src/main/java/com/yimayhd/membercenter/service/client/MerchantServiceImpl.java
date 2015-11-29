package com.yimayhd.membercenter.service.client;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.manager.MerchantServiceManager;
import com.yimayhd.membercenter.service.BussinessException;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.service.UserService;
import net.pocrd.entity.ApiReturnCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public MemResult<UserDO> registerUser(MerchantVO merchantVO) {
        LOGGER.info("rigisterUser merchantVO= {}", merchantVO);

        if (checkParam(merchantVO)) {
            LOGGER.error("parameter is not valid ,parameter={}", merchantVO);
            return MemResult.buildFailResult(MemberReturnCode.PARAMTER_ERROR.getCode(), MemberReturnCode.PARAMTER_ERROR.getDesc(), null);
        }

        UserDO userDO = new UserDO();
        userDO.setMobile(merchantVO.getMobile());
        com.yimayhd.user.client.result.BaseResult<UserDO> createUserResult = userService.createUserAndPutCache(userDO);

        //FIXME user不能返回api的错误code，应该自己定义
        if (ApiReturnCode._C_SUCCESS != Integer.valueOf(createUserResult.getErrorCode())) {
            LOGGER.info("invoke userService.createUserAndPutCache not success, merchantVO={}", merchantVO);
            return MemResult.buildFailResult(MemberReturnCode.USER_ERROR.getCode(), createUserResult.getResultMsg(), null);
        }

        UserDO createUserDO = createUserResult.getValue();
        LOGGER.info("createUserDO.getId={}", createUserDO.getId());

        BaseMerchantDO baseMerchantDO = merchantServiceManager.findBaseMerchantDOById(merchantVO.getMerchantId());
        if (null == baseMerchantDO) {
            LOGGER.info("merchant not found by merchantVO={}", merchantVO);
            return MemResult.buildFailResult(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR.getCode(),
                    MemberReturnCode.MERCHANT_NOT_FOUND_ERROR.getDesc(), null);
        }

        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setOpenId(merchantVO.getOpenId());
        wxUserMerchantRelationDO.setMerchantId(merchantVO.getMerchantId());
        wxUserMerchantRelationDO.setUserId(createUserDO.getId());

        Long id = merchantServiceManager.saveUserMerchantRelation(wxUserMerchantRelationDO);
        LOGGER.info("saved wxUserMerchantRelationDO={} id = {}", wxUserMerchantRelationDO, id);

        return MemResult.buildSuccessResult(createUserDO);
    }

    @Override
    public MemResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO) {
        LOGGER.info("findUserByOpenIdAndMerchant merchantVO = {}", merchantVO);

        if (StringUtils.isBlank(merchantVO.getOpenId()) || null == merchantVO.getMerchantId()) {
            LOGGER.error("parameter is not valid, openId={} and merchantId = {}",
                    merchantVO.getOpenId(), merchantVO.getMerchantId());
            return MemResult.buildFailResult(MemberReturnCode.PARAMTER_ERROR_C,
                    MemberReturnCode.PARAMTER_ERROR.getDesc(), null);
        }

        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setOpenId(merchantVO.getOpenId());
        wxUserMerchantRelationDO.setMerchantId(merchantVO.getMerchantId());
        UserDO userDO = null;
        try {
            userDO = merchantServiceManager.findMerchantUserDO(wxUserMerchantRelationDO);
        } catch (BussinessException e) {
            LOGGER.error("merchantServiceManager.findMerchantUserDO occur error:{}", e);
            return MemResult.buildFailResult(MemberReturnCode.SYSTEM_ERROR_C,
                    MemberReturnCode.SYSTEM_ERROR.getDesc(), null);
        }

        if (null == userDO) {
            return MemResult.buildFailResult(MemberReturnCode.USER_NOT_FOUND_C,
                    MemberReturnCode.USER_NOT_FOUND.getDesc(), null);
        }

        return MemResult.buildSuccessResult(userDO);
    }

    @Override
    public MemResult<List<UserDO>> findPageUsersByMerchant(MerchantPageQueryVO merchantPageQueryVO) {
        Long merchantUserId = merchantPageQueryVO.getMerchantUserId();
        if (null == merchantUserId) {
            LOGGER.error("merchantUserId is null");
            return MemResult.buildFailResult(MemberReturnCode.PARAMTER_ERROR_C,
                    MemberReturnCode.PARAMTER_ERROR.getDesc(), null);
        }

        List<UserDO> userDOList = new ArrayList<UserDO>();
        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setMerchantId(merchantPageQueryVO.getMerchantId());
        List<WxUserMerchantRelationDO> wxUserMerchantRelationDOList = merchantServiceManager.findByCondition(wxUserMerchantRelationDO);
        if (CollectionUtils.isEmpty(wxUserMerchantRelationDOList)) {
            return MemResult.buildSuccessResult(userDOList);
        }

        Set<Long> userIdSet = new HashSet<Long>();
        for (WxUserMerchantRelationDO relationDO : wxUserMerchantRelationDOList) {
            userIdSet.add(relationDO.getUserId());
        }



        return null;
    }

    private boolean checkParam(MerchantVO merchantVO) {
        return StringUtils.isBlank(merchantVO.getOpenId()) ||
                StringUtils.isBlank(merchantVO.getMobile()) || null == merchantVO.getMerchantId();
    }
}
