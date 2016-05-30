package com.yimayhd.membercenter.biz;

import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.login.LoginResult;

public interface MemberUserBiz {
	/**
	 * 会员登录操作
	 * @param userId
	 * @return
	 */
	public MemResult<LoginResult>  login(Long userId);
	
	/**
	 * 获取用户信息
	 * @param openId
	 * @param merchantId
	 * @return
	 */
	public MemResult<UserDO>   getUser(String openId,Long merchantId);
	 
	/**
	 * 获取用户信息
	 * @return
	 */
	public MemResult<UserDO>   getUser();
	
	/**
	 * 缓存会员信息
	 * @param vo
	 * @return
	 */
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo);
	
	/**
	 * 根据传入的key设置缓存信息
	 * @param vo
	 * @param key
	 * @return
	 */
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo,String key);
	
	/**
	 * 注册会员信息
	 * @param openId
	 * @param merchantId
	 * @return
	 */
	public  MemResult<UserDO> register(String openId,Long merchantId);
	
	/**
	 * 删除缓存的会员信息
	 * @return
	 */
	public MemResult<Boolean>  removeCacheMemberInfo();
	
	/**
	 * 判断是否需要自动注册
	 * @param openId
	 * @param merchantId
	 * @return
	 */
	public boolean isNeedAutoReg(String openId, Long merchantId);
	
	/**
	 * 跟新用户信息
	 * @param userVO
	 * @return
	 */
	public MemResult<Boolean> updateUser(UserVO userVO);
	
	/**
	 * 获取二维码
	 * @return
	 */
	public MemResult<String> getTwoDimensionCode();
	
	/**
	 * 获取二维码
	 * @param merchantId
	 * @param userId
	 * @return
	 */
	public MemResult<String> getTwoDimensionCode(Long merchantId,Long userId);
	
	/**
	 * 获取缓存的会员信息
	 * @return
	 */
	public MemeberBasicInfoVO getCachedMemberInfo();
	
	/**
	 * 根据key查询缓存的会员信息
	 * @param key
	 * @return
	 */
	public MemeberBasicInfoVO getCachedMemberInfo(String key);
	
	/**
	 * 校验短信验证码
	 * @param phone
	 * @param smsCode
	 * @return
	 */
	public MemResult<Boolean> verifyPhoneSmsCode(String phone,String smsCode);
	
	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	public MemResult<Boolean>  sendPhoneVerifyCode(String phone);
	
	/**
	 * 注册
	 * @return
	 */
	public MemResult<UserDO> register();
	
}
