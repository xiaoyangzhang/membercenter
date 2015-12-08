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
import com.yimay.integral.client.service.PointService;
import com.yimayhd.membercenter.Constants;
import com.yimayhd.membercenter.Converter;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.utils.Asserts;
import com.yimayhd.membercenter.utils.TimeElapseCaculate;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import com.yimayhd.user.session.manager.SessionConstant;
import com.yimayhd.user.session.manager.SessionUtils;

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
	private UserService userService;

	@Resource
	private MerchantService merchantService;

	@Resource
	private PointService pointService;

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

		MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO) SessionUtils.getSession()
				.getAttribute(Constants.MEMBER_USER_INFO);

		ModelAndView mv = new ModelAndView();
		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}
		// 更新用户信息
		userVO.setUserId(sessionInfo.getUserId());
		UserDO userDO = Converter.contertToUserDO(userVO);
		BaseResult<Boolean> result = userService.updateUserDO(userDO);

		LOGGER.debug("result:{}", JSON.toJSONString(result));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " during getUserDOByMobile and createUser:{}ms",
					TimeElapseCaculate.endSnapshort());
		}

		if (!result.isSuccess()) {
			LOGGER.error("error happen in userService.updateUserDO,result={},userVO={},memeberInfo={}",
					JSON.toJSONString(result), JSON.toJSONString(userVO), JSON.toJSONString(memeberInfo));
			mv.addObject("errorCode", result.getErrorCode());
			mv.addObject("message", result.getResultMsg());
			mv.setViewName("error");
			return mv;
		}

		// 处理成功之后跳转到二维码页面
		mv.addObject("memeberInfo", memeberInfo);
		mv.addObject("isFilledUserInfo", true);

		// 此处获取二维码串
		BaseResult<String> dimensionResult = userService.getTwoDimensionCode(sessionInfo.getUserId(),
				sessionInfo.getMerchantId());
		LOGGER.debug("dimensionResult:{}", JSON.toJSONString(dimensionResult));

		if (!dimensionResult.isSuccess()) {
			LOGGER.error(
					"error happen in userService.getTwoDimensionCode,dimensionResult={},userVO={},memeberInfo={},userId={},merchantId={}",
					JSON.toJSONString(dimensionResult), JSON.toJSONString(userVO), JSON.toJSONString(memeberInfo),
					sessionInfo.getUserId(), sessionInfo.getMerchantId());
			mv.addObject("message", dimensionResult.getErrorMsg());
			mv.addObject("errorCode", dimensionResult.getErrorCode());
			mv.setViewName("error");
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
	public ModelAndView toDimensionCodeView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("MemeberBasicInfoVO:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getPhone(), "phone");

		String phone = memeberInfo.getPhone();
		ModelAndView mv = new ModelAndView();

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}

		MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO) SessionUtils.getSession()
				.getAttribute(Constants.MEMBER_USER_INFO);
		// 生成会员信息、用户信息
		MerchantVO merchantVO = new MerchantVO();

		merchantVO.setMerchantUserId(sessionInfo.getMerchantId());
		merchantVO.setMobile(phone);
		merchantVO.setOpenId(sessionInfo.getOpenId());

		MemResult<UserDO> memResult = merchantService.registerUser(merchantVO);
		LOGGER.debug("memResult:{}", JSON.toJSONString(memResult));

		if (!memResult.isSuccess()) {
			LOGGER.error("error happen in merchantService.registerUser,memResult={},memeberInfo={},merchantVO={}",
					JSON.toJSONString(memResult), JSON.toJSONString(memeberInfo), JSON.toJSONString(merchantVO));
			mv.addObject("message", memResult.getErrorMsg());
			mv.addObject("errorCode", memResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}

		Long userId = memResult.getValue().getId();
		// 生成会话信息
		sessionInfo.setUserId(userId);
		sessionInfo.setPhone(memResult.getValue().getMobile());
		SessionUtils.getSession().setAttribute(Constants.MEMBER_USER_INFO, sessionInfo);

		// 生成用户凭证
		SessionUtils.getSession().setAttribute(SessionConstant.USER_ID, userId);

		// 此处获取二维码串
		BaseResult<String> dimensionResult = userService.getTwoDimensionCode(userId, sessionInfo.getMerchantId());
		LOGGER.debug("dimensionResult:{}", JSON.toJSONString(dimensionResult));

		if (!dimensionResult.isSuccess()) {
			LOGGER.error("error happen in userService.getTwoDimensionCode,dimensionResult={},userId={},merchantId={}",
					JSON.toJSONString(dimensionResult), userId, sessionInfo.getMerchantId());
			mv.addObject("message", dimensionResult.getErrorMsg());
			mv.addObject("errorCode", dimensionResult.getErrorCode());
			mv.setViewName("error");
			return mv;
		}

		String codeInfo = dimensionResult.getValue();
		LOGGER.debug("codeInfo:{}", codeInfo);

		// 查询用户信息，设置是否需要补全用户资料
		UserDO userDO = userService.getUserDOById(userId);
		LOGGER.debug("userDO:{}", JSON.toJSONString(userDO));

		if (userDO != null && !StringUtils.isEmpty(userDO.getName())) {
			mv.addObject("isFilledUserInfo", true);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " during getTwoDimensionCode:{}ms", TimeElapseCaculate.endSnapshort());
		}

		mv.addObject("codeInfo", codeInfo);
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

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}

		// 发送短信
		BaseResult<Boolean> result = userService.sendPhoneVerifyCode(memeberInfo.getPhone());
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
		BaseResult<Boolean> result = userService.validatePhoneVerifyCode(memeberInfo.getPhone(), authCode);
		LOGGER.debug("result:{}", JSON.toJSONString(result));

		if (!result.isSuccess() || result.getValue().booleanValue() == false) {
			LOGGER.error("error happen in userService.validatePhoneVerifyCode,result={},phone={},authCode={}",
					JSON.toJSONString(result), memeberInfo.getPhone(), authCode);
			return new Response().failure(result.getErrorMsg(), result.getErrorCode() + "");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " validatePhoneVerifyCode:{}ms" + TimeElapseCaculate.endSnapshort());
		}

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
	public ModelAndView registerMain(Long MERCHANTID, String OPENID, HttpServletRequest request) {
		MemeberBasicInfoVO memeberInfo = new MemeberBasicInfoVO();
		memeberInfo.setOpenId(OPENID);
		memeberInfo.setMerchantId(MERCHANTID);

		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));

		Asserts.AssertNotNull(memeberInfo, "memeberInfo");
		Asserts.AssertStringNotEmpty(memeberInfo.getOpenId(), "openId");
		Asserts.AssertNotNull(memeberInfo.getMerchantId(), "merchantId");

		ModelAndView mv = new ModelAndView();

		if (LOGGER.isDebugEnabled()) {
			TimeElapseCaculate.startSnapshort();
		}
		// 判断会员是否已经注册，注册后就跳转到二维码页面
		// userService 根据openId,merchantId查询
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setOpenId(memeberInfo.getOpenId());
		merchantVO.setMerchantUserId(memeberInfo.getMerchantId());
		
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		
		LOGGER.debug("userDO:{}", JSON.toJSONString(memResult));
		
		//判断用户是否存在
		
		if (memResult.getValue() != null) { //用户存在
			
			memeberInfo.setUserId(memResult.getValue().getId());
			memeberInfo.setPhone(memResult.getValue().getMobile());
			memeberInfo.setName(memResult.getValue().getName());
		
			// 标记用户登录
			SessionUtils.getSession().setAttribute(SessionConstant.USER_ID, memResult.getValue().getId());
			mv.addObject("userId", memResult.getValue().getId());
			mv.setViewName("/user/showTwoDimensionCode");
			
			if(!memResult.isSuccess() && memResult.getErrorCode() == MemberReturnCode.USER_NOT_REGISTER_C){//未注册
				//调用注册接口
				BaseResult<String> phoneResult = userService.findMobileByUserId(memResult.getValue().getId());
				
				merchantVO.setMobile(phoneResult.getValue());
				MemResult<UserDO> registerResult = merchantService.registerUser(merchantVO);
				
				if(!registerResult.isSuccess()){
					LOGGER.error("error happen in merchantService.registerUser,registerResult={},merchantVO={}",JSON.toJSONString(registerResult),JSON.toJSONString(merchantVO));
					mv.addObject("message", registerResult.getErrorMsg());
					mv.addObject("errorCode", registerResult.getErrorCode());
					mv.setViewName("error");
					return mv;
				}
			}
			
			// 获取二维码信息
			BaseResult<String> codeInfo = userService.getTwoDimensionCode(memResult.getValue().getId(),
					memeberInfo.getMerchantId());
			LOGGER.debug("codeInfo:{}", JSON.toJSONString(codeInfo));

			if (!codeInfo.isSuccess()) {
				LOGGER.error("error happen in userService.getTwoDimensionCode,codeInfo={},userId={},merchantId={}",
						JSON.toJSONString(codeInfo), memResult.getValue().getId(), memeberInfo.getMerchantId());
				mv.addObject("message", codeInfo.getErrorMsg());
				mv.addObject("errorCode", codeInfo.getErrorCode());
				mv.setViewName("error");
				return mv;
			}

			mv.addObject("codeInfo", codeInfo.getValue());

			if (!StringUtils.isEmpty(memResult.getValue().getName())) {
				mv.addObject("isFilledUserInfo", true);
			}

			// 存储OpenId&MerchantId
			SessionUtils.getSession().setAttribute(Constants.MEMBER_USER_INFO, memeberInfo);

			return mv;
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(TIME_ELAPSE_HEAD + " findUserDOByOpenIdAndMerchantId and getTwoDimensionCode:{}ms",
					TimeElapseCaculate.endSnapshort());
		}

		// 存储OpenId&MerchantId
		SessionUtils.getSession().setAttribute(Constants.MEMBER_USER_INFO, memeberInfo);

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
	public ModelAndView toFullfillUserInfoView(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:{}", JSON.toJSONString(memeberInfo));
		MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO) SessionUtils.getSession()
				.getAttribute(Constants.MEMBER_USER_INFO);

		ModelAndView mv = new ModelAndView();

		mv.addObject("phone", sessionInfo.getPhone());
		mv.setViewName("user/fulfillUserInfo");

		return mv;
	}

}
