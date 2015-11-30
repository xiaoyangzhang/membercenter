package com.yimayhd.membercenter.controller;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yimay.integral.client.service.PointService;
import com.yimayhd.membercenter.Constants;
import com.yimayhd.membercenter.Converter;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.exception.InValidParamException;
import com.yimayhd.membercenter.utils.Asserts;
import com.yimayhd.membercenter.utils.CommonUtil;
import com.yimayhd.membercenter.utils.TimeElapseCaculate;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;

/**
 * 
 * @Description 会员用户相关controller
 * @author zhang jian
 * @since 2015年11月18日
 * @version V1.0
 */
@RestController
public class UserInfoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	private static final String TIME_ELAPSE_HEAD=Constants.TIME_ELAPSE_HEAD;

	@Resource
	private UserService userService;
	
	@Resource 
	private MerchantService merchantService;
	
	@Resource
	private PointService pointService;

	/**
	 * 
	 * @Title          fulfillUserInfo 
	 * @Description    补全会员资料
	 * @param userVO
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/fulfillUserInfo")
	public ModelAndView fulfillUserInfoView(UserVO userVO,MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("user:" + CommonUtil.toJson(userVO));
		LOGGER.debug("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";

		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertNotNull(userVO, "userVO");
		} catch (InValidParamException e) {
			succeeded = false;
			message = e.getMessage();

			LOGGER.error(e.getMessage());
		} finally {

		}
		
		ModelAndView mv = new ModelAndView();
		if (!succeeded) {
			mv.addObject("message",message);
			mv.setViewName("error");
			return mv;
		}
		

		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		// 更新用户信息
		UserDO userDO = Converter.contertToUserDO(userVO);
		BaseResult<Boolean> result = userService.updateUserDO(userDO);
					
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " during getUserDOByMobile and createUser:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}

		if(!result.isSuccess()){
			mv.addObject("errorCode",result.getErrorCode());
			mv.addObject("message",result.getResultMsg());
			mv.setViewName("error");
			return mv;
		}
		
		//处理成功之后跳转到二维码页面
		mv.addObject("memeberInfo",memeberInfo);
		mv.addObject("isFilledUserInfo",true);
		//此处获取二维码串
		//userService.getTwoDimensionCode(userId,merchanId);
		
		String codeInfo = "18611865094";
		
		LOGGER.debug("codeInfo:" + codeInfo);
		
		mv.addObject("codeInfo",codeInfo);
		mv.setViewName("user/showTwoDimensionCode");
		
		return mv;
	}

	/**
	 * 
	 * @Title          toDimensionCodeView 
	 * @Description    跳转到二维码显示页面
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/toDimensionCode")
	public ModelAndView toDimensionCodeView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("MemeberBasicInfoVO:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";

		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		} catch (InValidParamException e) {
			succeeded = false;
			message = e.getMessage();
			LOGGER.error(e.getMessage());

		}finally{
			
		}
		
		ModelAndView mv = new ModelAndView();
		if (!succeeded) {
			mv.addObject("message", message);
			mv.setViewName("error");
			return mv;
		}
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		 
		
		//生成会员信息、用户信息
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setMerchantId(memeberInfo.getMerchantId());
		merchantVO.setMobile(memeberInfo.getPhone());
		merchantVO.setOpenId(memeberInfo.getOpenId());
		
		MemResult<UserDO> memResult = merchantService.registerUser(merchantVO);
		if(!memResult.isSuccess()){
			mv.addObject("message", memResult.getErrorMsg());
			mv.addObject("errorCode", memResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}
		
		memeberInfo.setUserId(memResult.getValue().getId());
		
		// 此处获取二维码串
		BaseResult<String> dimensionResult = userService.getTwoDimensionCode(memResult.getValue().getId(),memeberInfo.getMerchantId());
		if(!dimensionResult.isSuccess()){
			mv.addObject("message", dimensionResult.getErrorMsg());
			mv.addObject("errorCode", dimensionResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}
		
		String codeInfo = dimensionResult.getValue();
		LOGGER.debug("codeInfo:" + codeInfo);
		
		//查询用户信息，设置是否需要补全用户资料
		UserDO userDO = userService.getUserDOById(memeberInfo.getUserId());
		
		if(userDO != null && !StringUtils.isEmpty(userDO.getName())){
			mv.addObject("isFilledUserInfo",true);
		}
				
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " during getTwoDimensionCode:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}
		
		mv.addObject("codeInfo", codeInfo);
		mv.addObject("memeberInfo",memeberInfo);
		
		mv.setViewName("user/showTwoDimensionCode");

		return mv;
	}

	
	/**
	 * 
	 * @Title          sendMsgCode 
	 * @Description    发送短信验证码
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/sendMsgCode")
	public Response sendMsgCode(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";

		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		} catch (InValidParamException e) {
			LOGGER.error("memeberInfo" + CommonUtil.toJson(memeberInfo));
			return new Response().failure(e.getMessage());
		}
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		
		if (!succeeded) {
			return new Response().failure(message);
		}
		
		// 发送短信
		BaseResult<Boolean> result = userService.sendPhoneVerifyCode(memeberInfo.getPhone());
		if(!result.isSuccess()){
			LOGGER.error("发送短信失败:[errorCode:"  + result.getErrorCode() + ",message:" + result.getResultMsg() + "]");
			return new Response().failure(result.getErrorMsg(), result.getErrorCode());
		}
		
		//userService.sendPhoneVerifyCode(phone);
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " sendPhoneVerifyCode:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}

		return new Response().success();
	}

	/**
	 * 
	 * @Title          checkMsgCode 
	 * @Description    校验短信验证码
	 * @param memeberInfo
	 * @param authCode
	 * @return
	 */
	@RequestMapping(value = "/checkMsgCode")
	public Response checkMsgCode(MemeberBasicInfoVO memeberInfo,String authCode) {
		LOGGER.debug("memeberInfo" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";

		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
			Asserts.AssertStringNotEmpty(authCode, "authCode");
		} catch (InValidParamException e) {
			LOGGER.error("memeberInfo=" + CommonUtil.toJson(memeberInfo) + ",authCode=" + authCode);
			return new Response().failure(e.getMessage());

		}

		if (!succeeded) {
			return new Response().failure(message);
		}
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		// 校验短信验证码
		BaseResult<Boolean> result = userService.validatePhoneVerifyCode(memeberInfo.getPhone(), authCode);
		LOGGER.debug("result:" + CommonUtil.toJson(result));
		
		if(!result.isSuccess() || result.getValue().booleanValue() == false){
			LOGGER.error("短信验证失败:[errorCode:"  + result.getErrorCode() + ",message:" + result.getResultMsg() + "]");
			return new Response().failure(result.getErrorMsg(), result.getErrorCode());
		}
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " validatePhoneVerifyCode:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}

		return new Response().success();
	}

	/**
	 * 
	 * @Title registerMain
	 * @Description 注册主入口，获取用户的openId,商户公众号的唯一标识传递到页面中
	 * @param openId
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView registerMain(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";
		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getOpenId(), "openId");
			Asserts.AssertNotNull(memeberInfo.getMerchantId(), "merchantId");
		} catch (InValidParamException e) {
			succeeded = false;
			message = e.getMessage();

			LOGGER.error("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		}
		
		ModelAndView mv = new ModelAndView();

		if (!succeeded) {
			mv.addObject("message", message);
			mv.setViewName("error");
			return mv;
		}

		mv.addObject("memeberInfo", memeberInfo);
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		//判断会员是否已经注册，注册后就跳转到二维码页面
		//userService 根据openId,merchantId查询
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setOpenId(memeberInfo.getOpenId());
		merchantVO.setMerchantId( memeberInfo.getMerchantId());
		
		MemResult<UserDO> userDO = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		LOGGER.debug("userDO:" + CommonUtil.toJson(userDO));
		
		if(userDO != null){
			mv.addObject("userId",userDO.getValue().getId());
			mv.setViewName("/user/showTwoDimensionCode");
			
			//获取二维码信息
			BaseResult<String> codeInfo =  userService.getTwoDimensionCode(userDO.getValue().getId(),memeberInfo.getMerchantId());
			mv.addObject("codeInfo",codeInfo);
			
			return mv;
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " findUserDOByOpenIdAndMerchantId and getTwoDimensionCode:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}
		
		mv.setViewName("user/registerMain");
		
		return mv;
	}

	@RequestMapping(value = "/toAuthCodeView")
	public ModelAndView toAuthCodeView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";
		
		try {
			Asserts.AssertNotNull(memeberInfo, "memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getOpenId(), "openId");
			Asserts.AssertNotNull(memeberInfo.getMerchantId(), "merchantId");
			Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		} catch (InValidParamException e) {
			succeeded = false;
			message = e.getMessage();
			
			LOGGER.error("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		}

		ModelAndView mv = new ModelAndView();
		if (!succeeded) {
			mv.addObject("message", message);
			mv.setViewName("error");
			return mv;
		}
		
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("memeberInfo", memeberInfo);
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		
		// 发送验证码
		// 发送短信
		BaseResult<Boolean> result = userService.sendPhoneVerifyCode(memeberInfo.getPhone());
		if (!result.isSuccess()) {
			mv.addObject("message", result.getErrorMsg());
			mv.addObject("errorCode",result.getErrorCode());
			mv.setViewName("error");
			return mv;
		}
		System.out.println("发送验证码成功");

		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " sendPhoneVerifyCode:[" + TimeElapseCaculate.endSnapshort() + "ms]");
		}
		
		mv.setViewName("user/checkAuthCode");
		
		return mv;
	}
	
	@RequestMapping(value = "/toFullfillUserInfo")
	public ModelAndView toFullfillUserInfoView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		
		boolean succeeded = true;
		String message = "";
		
		try {
			Asserts.AssertNotNull(memeberInfo,"memeberInfo");
			Asserts.AssertStringNotEmpty(memeberInfo.getOpenId(), "openId");
			Asserts.AssertNotNull(memeberInfo.getMerchantId(), "merchantId");
			Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		} catch (InValidParamException e) {
			succeeded = false;
			message = e.getMessage();
			
			LOGGER.error("memeberInfo:" + CommonUtil.toJson(memeberInfo));
		}
		
		ModelAndView mv = new ModelAndView();
		if (!succeeded) {
			mv.addObject("message", message);
			mv.setViewName("error");
			return mv;
		}
		
		mv.addObject("memeberInfo", memeberInfo);
		mv.setViewName("user/fulfillUserInfo");
		
		return mv;
	}
}