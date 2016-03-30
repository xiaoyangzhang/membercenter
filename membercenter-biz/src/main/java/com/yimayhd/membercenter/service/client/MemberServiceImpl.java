package com.yimayhd.membercenter.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.fhtd.logger.annot.MethodLogger;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MemberService;
import com.yimayhd.membercenter.manager.MemberManager;

public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger("MemberServiceImpl");
	@Autowired
	private MemberManager memberManager ;
	
	@Override
	@MethodLogger(isPrintArguments = true, isPrintResult = true)
	public MemResult<MemberDO> getMemberById(long id) {
		MemResult<MemberDO> result = new MemResult<MemberDO>() ;
		try {
			result = memberManager.getMemberById(id);
		} catch (Exception e) {
			logger.error("getMemberById failed!  id={}",id, e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result ;
	}

	@Override
	@MethodLogger(isPrintArguments = true, isPrintResult = true)
	public MemResult<Boolean> overdueMember(long id) {
		MemResult<Boolean> result = new MemResult<Boolean>() ;
		try {
			result = memberManager.overdueMember(id);
		} catch (Exception e) {
			logger.error("overdueMember failed!  id={}",id, e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result ;
	}

}
