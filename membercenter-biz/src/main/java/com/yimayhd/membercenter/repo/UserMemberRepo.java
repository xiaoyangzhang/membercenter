package com.yimayhd.membercenter.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.user.client.service.member.UserMemberService;

public class UserMemberRepo {
	private static final Logger logger = LoggerFactory.getLogger("UserMemberRepo");
	
//	@Autowired
//	private UserMemberService userMemberService;
	
	public MemResultSupport flagVip4User(long userId){
		MemResultSupport result = new MemResultSupport() ;
//		BaseResult<Boolean> addResult = userMemberService.addUserOption(userId, UserOptions.MEMBER_VIP);
//		if( addResult == null || !addResult.isSuccess() || addResult.getValue() == null || !addResult.getValue() ){
//			logger.error("addUserOption  userId={}, option={}, result={}", userId, UserOptions.MEMBER_VIP, JSON.toJSONString(addResult));
//			if( addResult != null && addResult.getReturnCode() != null &&  UserServiceHttpCode.USER_NOT_FOUND == addResult.getReturnCode()){
//				result.setReturnCode(MemberReturnCode.USER_NOT_FOUND);
//			}else{
//				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
//			}
//		}
		return result ;
		
	}
}
