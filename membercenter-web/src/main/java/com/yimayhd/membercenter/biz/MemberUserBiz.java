package com.yimayhd.membercenter.biz;

import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.login.LoginResult;

public interface MemberUserBiz {
	public MemResult<LoginResult>  login(Long userId);
	
	public MemResult<UserDO>   getUser(String openId,Long merchantId);
	
	public MemResult<UserDO>   getUser();
	
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo);
	
	public MemResult<String> cacheMemberInfo(MemeberBasicInfoVO vo,String key);
	
	public  MemResult<UserDO> register(String openId,Long merchantId);
	
	public MemResult<Boolean>  removeCacheMemberInfo();
	
	public boolean isNeedAutoReg(String openId, Long merchantId);
	
	public MemResult<Boolean> updateUser(UserVO userVO);
	
	public MemResult<String> getTwoDimensionCode();
	
	public MemResult<String> getTwoDimensionCode(Long merchantId,Long userId);
	
	public MemeberBasicInfoVO getCachedMemberInfo();
	
	public MemeberBasicInfoVO getCachedMemberInfo(String key);
	
	public MemResult<Boolean> verifyPhoneSmsCode(String phone,String smsCode);
	
	public MemResult<Boolean>  sendPhoneVerifyCode(String phone);
	
	public MemResult<UserDO> register();
	
}
