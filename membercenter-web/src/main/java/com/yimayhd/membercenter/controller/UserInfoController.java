package com.yimayhd.membercenter.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.Constants;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.biz.MemberUserBiz;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.utils.Asserts;
import com.yimayhd.membercenter.utils.TimeElapseCaculate;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;

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
	private static final String TIME_ELAPSE_HEAD = Constants.TIME_ELAPSE_HEAD;
	@Resource
	private MemberUserBiz memberUserBiz;
	

	/**
	 * 
	 * @Title fulfillUserInfo
	 * @Description 补全会员资料
	 * @param userVO
	 * @param memeberInfo
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/user/fulfillUserInfo")
	public ModelAndView fulfillUserInfoView(UserVO userVO, MemeberBasicInfoVO memeberInfo)
			throws UnsupportedEncodingException {
		LOGGER.debug("user:{}", JSON.toJSONString(userVO));
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));
		// String name = memeberInfo.getName();
		// System.out.println(new String(name.getBytes("iso8859-1"),"UTF-8"));
		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertNotNull(userVO, "userVO");
		
		ModelAndView mv = new ModelAndView();
		
		MemResult<Boolean> updateResult = memberUserBiz.updateUser(userVO);
		LOGGER.debug("updateResult={}",updateResult);
		
		if(updateResult.isSuccess() == false){
			mv.addObject("message", updateResult.getErrorMsg());
			mv.addObject("errorCode", updateResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}
		
		
		// 处理成功之后跳转到二维码页面
		mv.addObject("memeberInfo", memeberInfo);
		mv.addObject("nickName",userVO.getName());
		mv.addObject("isFilledUserInfo", true);

		// 此处获取二维码串
		MemResult<String> dimensionResult = memberUserBiz.getTwoDimensionCode();
		LOGGER.debug("dimensionResult:{}", JSON.toJSONString(dimensionResult));

		if (!dimensionResult.isSuccess()) {
			LOGGER.error(
					"error happen in userService.getTwoDimensionCode,dimensionResult={},userVO={},memeberInfo={}",
					JSON.toJSONString(dimensionResult), JSON.toJSONString(userVO), JSON.toJSONString(memeberInfo));
			
			return mv;
		}

		String codeInfo = dimensionResult.getValue();

		LOGGER.debug("codeInfo:{}", codeInfo);

		mv.addObject("codeInfo", codeInfo);
		mv.setViewName("user/showTwoDimensionCode");

		return mv;
	}

	/**
	 * 
	 * @Title toDimensionCodeView
	 * @Description 跳转到二维码显示页面
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/user/toDimensionCode")
	public ModelAndView toDimensionCodeView(MemeberBasicInfoVO memeberInfo, HttpServletRequest request) {
		LOGGER.debug("MemeberBasicInfoVO:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		
		ModelAndView mv = new ModelAndView();

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}
		
		memberUserBiz.register();
		MemeberBasicInfoVO cachedMemberInfo = memberUserBiz.getCachedMemberInfo();
		MemResult<UserDO> loginResult = memberUserBiz.login(cachedMemberInfo.getOpenId(), cachedMemberInfo.getMerchantId());
		//设置登录状态

		if (!loginResult.isSuccess()) {
			LOGGER.error("error happen in merchantService.registerUser,loginResult={},memeberInfo={}",
					JSON.toJSONString(loginResult), JSON.toJSONString(memeberInfo));
			mv.addObject("message", loginResult.getErrorMsg());
			mv.addObject("errorCode", loginResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}
		
		// 此处获取二维码串
		MemResult<String> dimensionResult = memberUserBiz.getTwoDimensionCode();
		LOGGER.debug("dimensionResult:{}", JSON.toJSONString(dimensionResult));

		if (!dimensionResult.isSuccess()) {
			LOGGER.error("error happen in userService.getTwoDimensionCode,dimensionResult={}",JSON.toJSONString(dimensionResult));
			mv.addObject("message", dimensionResult.getErrorMsg());
			mv.addObject("errorCode", dimensionResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}

		String codeInfo = dimensionResult.getValue();
		LOGGER.debug("codeInfo:{}", codeInfo);

		// 查询用户信息，设置是否需要补全用户资料
		MemResult<UserDO> userDOResult = memberUserBiz.getUser();
		LOGGER.debug("userDOResult:{}", JSON.toJSONString(userDOResult));

		if (userDOResult.isSuccess()  && !StringUtils.isEmpty(userDOResult.getValue().getNickname())) {
			mv.addObject("isFilledUserInfo", true);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " during getTwoDimensionCode:{}ms", TimeElapseCaculate.endSnapshort());
		}

		mv.addObject("codeInfo", codeInfo);
		mv.addObject("nickName",userDOResult.getValue().getNickname());
		mv.addObject("phone",userDOResult.getValue().getMobile());
		// mv.addObject("memeberInfo",memeberInfo);

		mv.setViewName("user/showTwoDimensionCode");

		return mv;
	}

	/**
	 * 
	 * @Title sendMsgCode
	 * @Description 发送短信验证码
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/user/sendMsgCode")
	public Response sendMsgCode(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		
		/**
		//判断输入的手机号码是否一致
		String requestPhone = memeberInfo.getPhone();
		if(StringUtils.isEmpty(requestPhone) || !requestPhone.equals(memeberInfo.getPhone())){
			return new Response().failure("发送短信的手机号码与注册手机号码不一致!");
		}
		*/
		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}

		MemResult<Boolean> result = memberUserBiz.sendPhoneVerifyCode(memeberInfo.getPhone());
		LOGGER.debug("result:{}", JSON.toJSONString(result));
		if (!result.isSuccess()) {
			LOGGER.error("error happen in userService.sendPhoneVerifyCode,result={},phone={}",
					JSON.toJSONString(result), memeberInfo.getPhone());
			return new Response().failure(result.getErrorMsg(), result.getErrorCode() + "");
		}

		// userService.sendPhoneVerifyCode(phone);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " sendPhoneVerifyCode:{}ms", TimeElapseCaculate.endSnapshort());
		}

		return new Response().success();
	}

	/**
	 * 
	 * @Title checkMsgCode
	 * @Description 校验短信验证码
	 * @param memeberInfo
	 * @param authCode
	 * @return
	 */
	@RequestMapping(value = "/user/checkMsgCode")
	public Response checkMsgCode(MemeberBasicInfoVO memeberInfo, String authCode) {
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));
		LOGGER.debug("authCode:{}", authCode);

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");
		Asserts.AssertStringNotEmpty(authCode, "authCode");

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}

		// 校验短信验证码
		MemResult<Boolean> result = memberUserBiz.verifyPhoneSmsCode(memeberInfo.getPhone(), authCode);
		LOGGER.debug("result:{}", JSON.toJSONString(result));

		if (!result.isSuccess() || result.getValue().booleanValue() == false) {
			LOGGER.error("error happen in userService.validatePhoneVerifyCode,result={},phone={},authCode={}",
					JSON.toJSONString(result), memeberInfo.getPhone(), authCode);
			return new Response().failure(result.getErrorMsg(), result.getErrorCode() + "");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " validatePhoneVerifyCode:{}ms" + TimeElapseCaculate.endSnapshort());
		}
		
		//完善缓存中的信息
		MemeberBasicInfoVO cachedMemberInfo = memberUserBiz.getCachedMemberInfo();
		cachedMemberInfo.setPhone(memeberInfo.getPhone());
		memberUserBiz.cacheMemberInfo(cachedMemberInfo);
		
		// MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO)
		// SessionUtils.getSession().getAttribute(Constants.MEMBER_USER_INFO);
		// sessionInfo.setPhone(memeberInfo.getPhone());
		// SessionUtils.getSession().setAttribute(Constants.MEMBER_USER_INFO,sessionInfo);

		return new Response().success();
	}

	/**
	 * 
	 * @Title registerMain
	 * @Description 注册主入口，获取用户的openId,商户公众号的唯一标识传递到页面中
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView registerMain(Long MERCHANTID, String OPENID) {
		MemeberBasicInfoVO memeberInfo = new MemeberBasicInfoVO();
		Long merchantId = MERCHANTID;
		String openId = OPENID;
		memeberInfo.setOpenId(openId);
		memeberInfo.setMerchantId(merchantId);
		//保存openid与商户编号
		memberUserBiz.removeCacheMemberInfo();
		memberUserBiz.cacheMemberInfo(memeberInfo);

		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getOpenId(), "openId");
		Asserts.AssertNotNull(memeberInfo.getMerchantId(), "merchantId");

		ModelAndView mv = new ModelAndView();

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}
		// 判断会员是否已经注册，注册后就跳转到二维码页面
		
		//登录 FIXME 需要优化，调用次数太多
		MemResult<UserDO> loginResult = memberUserBiz.login(openId, merchantId);
		if(loginResult.isSuccess()){
			// 获取二维码信息
			MemResult<String> codeInfo = memberUserBiz.getTwoDimensionCode();
			mv.addObject("codeInfo", codeInfo.getValue());
			mv.addObject("memeberInfo",memeberInfo);
			mv.addObject("nickName",loginResult.getValue().getNickname());
			mv.addObject("phone",loginResult.getValue().getMobile());
			mv.setViewName("/user/showTwoDimensionCode");
			
			if (!StringUtils.isEmpty(loginResult.getValue().getNickname())) {
				mv.addObject("isFilledUserInfo", true);
			}
			
			return mv;
		}else if(memberUserBiz.isNeedAutoReg(openId, merchantId)){
			//注册
			memberUserBiz.register(openId, merchantId);
			//登录
			// userService 根据openId,merchantId查询
			loginResult = memberUserBiz.login(openId, merchantId);
			MemResult<String> codeInfo = memberUserBiz.getTwoDimensionCode();
			mv.addObject("codeInfo", codeInfo.getValue());
			mv.addObject("memeberInfo",memeberInfo);
			mv.addObject("nickName",loginResult.getValue().getNickname());
			mv.addObject("phone",loginResult.getValue().getMobile());
			mv.setViewName("/user/showTwoDimensionCode");
			
			if (!StringUtils.isEmpty(loginResult.getValue().getNickname())) {
				mv.addObject("isFilledUserInfo", true);
			}
			//FIXME 判断登录结果
			return mv;
		}

		mv.setViewName("user/registerMain");

		return mv;
	}

	@RequestMapping(value = "/user/toAuthCodeView")
	public ModelAndView toAuthCodeView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");

		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("memeberInfo", memeberInfo);

		/**
		 * if(LOGGER.isDebugEnabled()){ TimeElapseCaculate.startSnapshort(); }
		 * 
		 * // 发送验证码 BaseResult<Boolean> result =
		 * userService.sendPhoneVerifyCode(memeberInfo.getPhone()); if
		 * (!result.isSuccess()) { mv.addObject("message",
		 * result.getErrorMsg());
		 * mv.addObject("errorCode",result.getErrorCode());
		 * mv.setViewName("error"); return mv; }
		 * 
		 * LOGGER.debug("result:{}",JSON.toJSONString(result));
		 * 
		 * 
		 * if(LOGGER.isDebugEnabled()){ LOGGER.debug(TIME_ELAPSE_HEAD +
		 * " sendPhoneVerifyCode:{}ms" + TimeElapseCaculate.endSnapshort()); }
		 */
		mv.setViewName("user/checkAuthCode");

		return mv;
	}

	@RequestMapping(value = "/user/toFullfillUserInfo")
	public ModelAndView toFullfillUserInfoView(MemeberBasicInfoVO memeberInfo,HttpServletRequest request) {
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));
		
		MemeberBasicInfoVO cacheMemberInfo =  memberUserBiz.getCachedMemberInfo();
		String phone = cacheMemberInfo.getPhone();

		ModelAndView mv = new ModelAndView();
		mv.addObject("phone", phone);
		mv.setViewName("user/fulfillUserInfo");

		return mv;
	}

}
