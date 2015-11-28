package com.yimayhd.membercenter.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.user.client.enums.UserOptions;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.member.UserMemberService;
import com.yimayhd.user.errorcode.UserServiceHttpCode;

public class UserMemberRepo {
	private static final Logger logger = LoggerFactory.getLogger("UserMemberRepo");
	
	@Autowired
	private UserMemberService userMemberService;
	
	public MemResultSupport addVipflag(long userId){
		MemResultSupport result = new MemResultSupport() ;
		UserOptions ops = UserOptions.MEMBER_VIP;
		BaseResult<Boolean> addResult = userMemberService.addUserOption(userId, ops);
		if( addResult == null || !addResult.isSuccess() || addResult.getValue() == null || !addResult.getValue() ){
			logger.error("addUserOption  userId={}, option={}, result={}", userId, ops, JSON.toJSONString(addResult));
			if( addResult != null && addResult.getReturnCode() != null &&  UserServiceHttpCode.USER_NOT_FOUND == addResult.getReturnCode()){
				result.setReturnCode(MemberReturnCode.USER_NOT_FOUND);
			}else{
				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			}
		}
		return result ;
	}
	public MemResultSupport removeVipFlag(long userId){
		MemResultSupport result = new MemResultSupport() ;
		UserOptions ops = UserOptions.MEMBER_VIP;
		BaseResult<Boolean> removeResult = userMemberService.removeUserOption(userId, ops);
		if( removeResult == null || !removeResult.isSuccess() || removeResult.getValue() == null || !removeResult.getValue() ){
			logger.error("removeUserOption  userId={}, option={}, result={}", userId, ops, JSON.toJSONString(removeResult));
			if( removeResult != null && removeResult.getReturnCode() != null &&  UserServiceHttpCode.USER_NOT_FOUND == removeResult.getReturnCode()){
				result.setReturnCode(MemberReturnCode.USER_NOT_FOUND);
			}else{
				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			}
		}
		return result ;
	}
}
