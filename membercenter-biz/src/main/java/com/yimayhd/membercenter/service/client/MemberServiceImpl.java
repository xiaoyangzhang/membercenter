package com.yimayhd.membercenter.service.client;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.annot.MethodLogger;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MemberService;
import com.yimayhd.membercenter.manager.MemberManager;

public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberManager memberManager ;
	
	@Override
	@MethodLogger(isHttpApi = false, isCatchException = false, isPrintArguments = true, isPrintResult = true)
	public MemResult<MemberDO> getMemberById(long id) {
		return memberManager.getMemberById(id);
	}

	@Override
	public MemResult<Boolean> overdueMember(long id) {
		return memberManager.overdueMember(id);
	}

}
