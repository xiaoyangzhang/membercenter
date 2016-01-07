package com.yimayhd.membercenter.service.client;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.yimayhd.membercenter.client.result.MemPageResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.converter.MemberConverter;
import com.yimayhd.membercenter.manager.MerchantServiceManager;
import com.yimayhd.membercenter.service.BussinessException;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.domain.UserDOPageQuery;
import com.yimayhd.user.client.result.BasePageResult;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import com.yimayhd.user.errorcode.UserServiceHttpCode;

import net.pocrd.entity.ApiReturnCode;

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

        WxUserMerchantRelationDO wxQueryCondition = new WxUserMerchantRelationDO();
        wxQueryCondition.setOpenId(merchantVO.getOpenId());
        wxQueryCondition.setMerchantUserId(merchantVO.getMerchantUserId());
        List<WxUserMerchantRelationDO> wxUserMerchantRelationDOList = merchantServiceManager.findByCondition(wxQueryCondition);
        if (CollectionUtils.isNotEmpty(wxUserMerchantRelationDOList)) {
            LOGGER.info("wxUserMerchantRelationDOList is not empty and size = {}, merchantVO={}", wxUserMerchantRelationDOList.size(), merchantVO);
            return MemResult.buildFailResult(MemberReturnCode.USER_ERROR.getCode(), MemberReturnCode.USER_ERROR.getDesc(), null);
        }

        BaseResult<UserDO> userResult = userService.getUserDOByMobile(merchantVO.getMobile());
        if (null == userResult.getValue() && UserServiceHttpCode.USER_NOT_FOUND.getCode() != userResult.getErrorCode()) {
            LOGGER.error("registerUser userService.getUserDOByMobile occur error by mobile = {}", merchantVO.getMobile());
            return MemResult.buildFailResult(MemberReturnCode.USER_ERROR.getCode(), userResult.getErrorMsg(), null);
        }


        UserDO createUserDO = parseUserDO(userResult, merchantVO);
        if (null == createUserDO) {
            LOGGER.info("parseUserDO return null");
            return MemResult.buildFailResult(MemberReturnCode.USER_ERROR.getCode(), MemberReturnCode.USER_ERROR.getDesc(), null);
        }

        LOGGER.info("createUserDO.getId={}", createUserDO.getId());
        BaseMerchantDO baseMerchantDO = merchantServiceManager.getBaseMerchantByMerchantUserId(merchantVO.getMerchantUserId());
        if (null == baseMerchantDO) {
            LOGGER.info("merchant not found by merchantVO={}", merchantVO);
            return MemResult.buildFailResult(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR.getCode(),
                    MemberReturnCode.MERCHANT_NOT_FOUND_ERROR.getDesc(), null);
        }

        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setOpenId(merchantVO.getOpenId());
        wxUserMerchantRelationDO.setMerchantUserId(merchantVO.getMerchantUserId());
        wxUserMerchantRelationDO.setUserId(createUserDO.getId());

        Long id = merchantServiceManager.saveUserMerchantRelation(wxUserMerchantRelationDO);
        LOGGER.info("saved wxUserMerchantRelationDO={} id = {}", wxUserMerchantRelationDO, id);

        return MemResult.buildSuccessResult(createUserDO);
    }

    @Override
    public MemResult<UserDO> findUserByOpenIdAndMerchant(MerchantVO merchantVO) {
        LOGGER.info("findUserByOpenIdAndMerchant merchantVO = {}", merchantVO);
        MemResult memResult = new MemResult();
        if (StringUtils.isBlank(merchantVO.getOpenId()) || null == merchantVO.getMerchantUserId()) {
            LOGGER.error("parameter is not valid, openId={} and merchantId = {}",
                    merchantVO.getOpenId(), merchantVO.getMerchantUserId());
            return MemResult.buildFailResult(MemberReturnCode.PARAMTER_ERROR_C,
                    MemberReturnCode.PARAMTER_ERROR.getDesc(), null);
        }
        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setOpenId(merchantVO.getOpenId());
        wxUserMerchantRelationDO.setMerchantUserId(merchantVO.getMerchantUserId());
        try {
            UserDO userDO = merchantServiceManager.findMerchantUserDO(wxUserMerchantRelationDO);
            if (null == userDO){
                wxUserMerchantRelationDO.setMerchantUserId(null);
                userDO = merchantServiceManager.findMerchantUserDO(wxUserMerchantRelationDO);
                if (null == userDO){
                    return MemResult.buildFailResult(MemberReturnCode.USER_NOT_FOUND_C,
                            MemberReturnCode.USER_NOT_FOUND.getDesc(), null);
                }else{
                    memResult.setValue(userDO);
                    memResult.setErrorCode(MemberReturnCode.USER_NOT_REGISTER_C);
                    memResult.setErrorMsg(MemberReturnCode.USER_NOT_REGISTER.getDesc());
                    memResult.setSuccess(false);
                    return memResult;
                }
            }else{
                return MemResult.buildSuccessResult(userDO);
            }
        } catch (BussinessException e) {
            LOGGER.error("merchantServiceManager.findMerchantUserDO occur error:{}", e);
            return MemResult.buildFailResult(MemberReturnCode.SYSTEM_ERROR_C,
                    MemberReturnCode.SYSTEM_ERROR.getDesc(), null);
        }
    }

    @Override
    public MemPageResult<UserDO> findPageUsersByMerchant(MerchantPageQueryVO merchantPageQueryVO) {
        MemPageResult<UserDO> memPageResult = new MemPageResult<UserDO>();

        Long merchantUserId = merchantPageQueryVO.getMerchantUserId();
        if (null == merchantUserId) {
            LOGGER.error("merchantUserId is null");
            memPageResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return memPageResult;
        }

        List<UserDO> userDOList = new ArrayList<UserDO>();
        WxUserMerchantRelationDO wxUserMerchantRelationDO = new WxUserMerchantRelationDO();
        wxUserMerchantRelationDO.setMerchantUserId(merchantUserId);
        List<WxUserMerchantRelationDO> wxUserMerchantRelationDOList = merchantServiceManager.findByCondition(wxUserMerchantRelationDO);
        if (CollectionUtils.isEmpty(wxUserMerchantRelationDOList)) {
            memPageResult.setList(userDOList);
            return memPageResult;
        }

        try{
            UserDOPageQuery userDOPageQuery = MemberConverter.do2UserDOPageQuery(merchantPageQueryVO,wxUserMerchantRelationDOList);
            memPageResult.setPageNo(merchantPageQueryVO.getPageNo());
            memPageResult.setPageSize(merchantPageQueryVO.getPageSize());

            BasePageResult<UserDO> basePageResult = userService.findPageResultByCondition(userDOPageQuery);
            memPageResult.setTotalCount(basePageResult.getTotalCount());
            memPageResult.setHasNext(basePageResult.isHasNext());
            memPageResult.setList(basePageResult.getList());
            return memPageResult;
        }catch (Exception e){
            LOGGER.error("userService.findPageResultByCondition(userDOPageQuery) Exception" + e);
            memPageResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
            return memPageResult;
        }
    }

    private UserDO parseUserDO(BaseResult<UserDO> userDOBaseResult, MerchantVO merchantVO) {
        if (null != userDOBaseResult.getValue()) {
            return userDOBaseResult.getValue();
        }

        UserDO userDO = new UserDO();
        userDO.setMobile(merchantVO.getMobile());
        com.yimayhd.user.client.result.BaseResult<UserDO> createUserResult = userService.createUserAndPutCache(userDO);
        if (ApiReturnCode._C_SUCCESS != Integer.valueOf(createUserResult.getErrorCode())) {
            LOGGER.info("invoke userService.createUserAndPutCache not success, merchantVO={}", merchantVO);
            return null;
        }

        return createUserResult.getValue();
    }

    private boolean checkParam(MerchantVO merchantVO) {
        return StringUtils.isBlank(merchantVO.getOpenId()) ||
                StringUtils.isBlank(merchantVO.getMobile()) || null == merchantVO.getMerchantUserId();
    }

	@Override
	public MemResult<WxUserMerchantRelationDO> findWxUserRelationByUserId(Long userId) {
		MemResult<WxUserMerchantRelationDO> result = new MemResult<WxUserMerchantRelationDO>();
		result.setSuccess(false);
		if(userId == null){
			result.setErrorMsg("userId is required!");
			LOGGER.error("userId is required!userId={}",userId);
			
			return result;
		}
		WxUserMerchantRelationDO queryDO = new WxUserMerchantRelationDO();
		queryDO.setUserId(userId);
		List<WxUserMerchantRelationDO> wxListResult = null;
		try{
			wxListResult = merchantServiceManager.findByCondition(queryDO);
			LOGGER.debug("wxListResult={}",JSONObject.toJSONString(wxListResult));
			if(!CollectionUtils.isEmpty(wxListResult)){
				result.setValue(wxListResult.get(0));
				result.setSuccess(true);
			}else{
				result.setErrorMsg("no data to find!");
			}
			
		}catch(Exception e){
			result.setErrorMsg(e.getMessage());
			LOGGER.error("findByCondition error,userId={},wxListResult={}",userId,wxListResult,e);
		}
				
		return result;
	}
}
