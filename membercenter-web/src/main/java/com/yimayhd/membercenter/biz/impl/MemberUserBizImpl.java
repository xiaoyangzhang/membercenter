package com.yimayhd.membercenter.biz.impl;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.yimay.integral.client.service.PointService;
import com.yimayhd.membercenter.Converter;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.biz.MemberUserBiz;
import com.yimayhd.membercenter.cache.CacheManager;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.result.login.LoginResult;
import com.yimayhd.user.client.service.UserService;
import com.yimayhd.user.session.manager.SessionManager;

public class MemberUserBizImpl implements MemberUserBiz{
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberUserBizImpl.class);
	public static final String USER_TOKEN_KEY = "USER_TOKEN_KEY";
	
	@Resource
	private CacheManager cacheManager;
	@Resource
	private SessionManager sessionManager;
	@Resource
	private UserService userService;
	@Resource
	private MerchantService merchantService;
	@Resource
	private PointService pointService;
	
	private final String MEMBER_INFO_CACHE_HEAD = "member_info_";
	private final int DEFAULT_CACHE_VALID_TIME = 60 * 30;
	private int cacheValidTime = DEFAULT_CACHE_VALID_TIME; //默认30分钟
	
	@Override
	public MemResult<LoginResult> login(Long userId) {
		sessionManager.removeToken(getRequest());
		MemResult<LoginResult> loginResult = new MemResult<LoginResult>();
		// 触发登录操作
		LoginResult result = userService.loginByUserId(userId);
		if (result.isSuccess()) {
			// 设置cookie信息
			String token = result.getToken();
			Cookie cookie = new Cookie("token", token);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			getResponse().addCookie(cookie);
		}
		loginResult.setValue(result);

		return loginResult;
	}
	
	public boolean isNeedAutoReg(String openId, Long merchantId){
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setOpenId(openId);
		merchantVO.setMerchantUserId(merchantId);
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		if(!memResult.isSuccess() && memResult.getErrorCode() == MemberReturnCode.USER_NOT_REGISTER_C){
			return true;
		}
		
		return false;
	}
	
	public MemResult<UserDO> register(String openId, Long merchantId ){
		MemResult<UserDO> result = new MemResult<UserDO>();
		//调用注册接口
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setOpenId(openId);
		merchantVO.setMerchantUserId(merchantId);
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		MemResult<UserDO> registerResult = null;
		if(!memResult.isSuccess() && memResult.getErrorCode() == MemberReturnCode.USER_NOT_REGISTER_C){//有用户但是未绑定微信
			BaseResult<String> phoneResult = userService.findMobileByUserId(memResult.getValue().getId());
			merchantVO.setMobile(phoneResult.getValue());
			registerResult = merchantService.registerUser(merchantVO);
			
		}else if(!memResult.isSuccess()){ //新用户注册
			//从缓存中获取手机号码
			String phone = getCachedMemberInfo().getPhone();
			merchantVO.setMobile(phone);
			registerResult = merchantService.registerUser(merchantVO);
		}
		
		if(registerResult.isSuccess() == false){
			result.setSuccess(false);
			result.setErrorMsg(registerResult.getErrorMsg());
		}else{
			result.setValue(registerResult.getValue());
		}
		
		return result;
	}

	@Override
	public MemResult<UserDO> getUser() {
		MemResult<UserDO> result = new MemResult<UserDO>();
		UserDO userdo = userService.getUserDOById(sessionManager.getUserId());
		result.setValue(userdo);
		
		return result;
	}

	@Override
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo) {
		HttpServletRequest request =  getRequest();
		String key = sessionManager.getTokenFromCookie(request);
		boolean result = cacheManager.addToTair(MEMBER_INFO_CACHE_HEAD + key,vo,cacheValidTime);
		MemResult<String> memResult = new  MemResult<String>();
		memResult.setSuccess(result);
		memResult.setValue(key);
		
		return memResult;
	}
	
	@Override
	public MemResult<Boolean> removeCacheMemberInfo() {
		HttpServletRequest request =  getRequest();
		String key = sessionManager.getTokenFromCookie(request);
		boolean result = cacheManager.deleteFromTair(MEMBER_INFO_CACHE_HEAD + key);
		MemResult<Boolean> memResult = new  MemResult<Boolean>();
		memResult.setSuccess(result);
		
		return memResult;
	}
	
	public HttpServletRequest getRequest(){
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attrs != null){
			return attrs.getRequest();
		}
		
		return null;
	}
	
	public HttpServletResponse getResponse(){
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attrs != null){
			return attrs.getResponse();
		}
		
		return null;
	}


	@Override
	public MemResult<Boolean> updateUser(UserVO userVO) {
		if(userVO.getUserId() == null){
			userVO.setUserId(sessionManager.getUser().getId());
		}
		UserDO userDO = Converter.contertToUserDO(userVO);
		BaseResult<Boolean> result = userService.updateUserDO(userDO);
		MemResult<Boolean> updateResult = new MemResult<Boolean>();
		
		updateResult.setSuccess(result.isSuccess());
		
		return updateResult;
	}

	@Override
	public MemResult<String> getTwoDimensionCode() {
		MemResult<String> result = new MemResult<String>();
		result.setSuccess(false);
		
		MemeberBasicInfoVO memberInfo = getCachedMemberInfo();
		if(memberInfo != null){
			Long merchantId = memberInfo.getMerchantId();
			Long userId = memberInfo.getUserId();
			if(userId == null){
				userId = sessionManager.getUser().getId();
			}
			
			BaseResult<String> dimensionResult = userService.getTwoDimensionCode(userId,merchantId);
			if(dimensionResult.isSuccess()){
				result.setSuccess(true); 
				result.setValue(dimensionResult.getValue());
			}
		}
		return result;
	}

	@Override
	public MemeberBasicInfoVO getCachedMemberInfo() {
		HttpServletRequest request =  getRequest();
		String key = sessionManager.getTokenFromCookie(request);
		MemeberBasicInfoVO memberInfo = (MemeberBasicInfoVO) (cacheManager.getFormTair((MEMBER_INFO_CACHE_HEAD + key)));
		
		return memberInfo;
	}

	@Override
	public MemResult<Boolean> verifyPhoneSmsCode(String phone, String smsCode) {
		MemResult<Boolean> verifyResult = new MemResult<Boolean>();
		verifyResult.setSuccess(false);
		BaseResult<Boolean> result = userService.validatePhoneVerifyCode(phone, smsCode);
		if(result.isSuccess()){
			verifyResult.setSuccess(true);
		}
		return verifyResult;
	}

	@Override
	public MemResult<Boolean> sendPhoneVerifyCode(String phone) {
		// 发送短信
		MemResult<Boolean> sendResult = new MemResult<Boolean>();
		sendResult.setSuccess(false);
		BaseResult<Boolean> result = userService.sendPhoneVerifyCode(phone);
		sendResult.setSuccess(result.isSuccess());
		
		return sendResult;
	}

	@Override
	public MemResult<UserDO> register() {
		MemResult<UserDO> result = new MemResult<UserDO>();
		
		//从缓存中获取手机号码
		MemeberBasicInfoVO cachedMemberInfo  = getCachedMemberInfo();
		
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setMobile(cachedMemberInfo.getPhone());
		merchantVO.setOpenId(cachedMemberInfo.getOpenId());
		merchantVO.setMerchantUserId(cachedMemberInfo.getMerchantId());
		
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		MemResult<UserDO> registerResult = null;
		if(!memResult.isSuccess() ){//有用户但是未绑定微信
			registerResult = merchantService.registerUser(merchantVO);
			
		}
		
		if(registerResult.isSuccess() == false){
			result.setSuccess(false);
			result.setErrorMsg(registerResult.getErrorMsg());
		}else{
			result.setValue(registerResult.getValue());
		}
		
		return result;
	}

	@Override
	public MemResult<UserDO> getUser(String openId, Long merchantId) {
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setOpenId(openId);
		merchantVO.setMerchantUserId(merchantId);
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		
		return memResult;
	}

	@Override
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo, String key) {
//		HttpServletRequest request =  getRequest();
//		String key = sessionManager.getTokenFromCookie(request);
		boolean result = cacheManager.addToTair(MEMBER_INFO_CACHE_HEAD + key,vo,cacheValidTime);
		MemResult<String> memResult = new  MemResult<String>();
		memResult.setSuccess(result);
		memResult.setValue(key);
		
		return memResult;
	}

	@Override
	public MemeberBasicInfoVO getCachedMemberInfo(String key) {
//		HttpServletRequest request =  getRequest();
//		String key = sessionManager.getTokenFromCookie(request);
		MemeberBasicInfoVO memberInfo = (MemeberBasicInfoVO) (cacheManager.getFormTair((MEMBER_INFO_CACHE_HEAD + key)));
		
		return memberInfo;
	}

	@Override
	public MemResult<String> getTwoDimensionCode(Long merchantId,Long userId) {
		MemResult<String> result = new MemResult<String>();
		result.setSuccess(false);

		if (userId == null) {
			userId = sessionManager.getUser().getId();
		}

		BaseResult<String> dimensionResult = userService.getTwoDimensionCode(userId, merchantId);
		if (dimensionResult.isSuccess()) {
			result.setSuccess(true);
			result.setValue(dimensionResult.getValue());
		}

		return result;
	}

}
