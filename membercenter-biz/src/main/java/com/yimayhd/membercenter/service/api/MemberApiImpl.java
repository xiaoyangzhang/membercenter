package com.yimayhd.membercenter.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.annot.MethodLogger;
import com.yimayhd.membercenter.api.MemberApi;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.entity.member.MemberDetail;
import com.yimayhd.membercenter.entity.member.MemberPurchauseDetail;
import com.yimayhd.membercenter.manager.MemberManager;

import net.pocrd.dubboext.DubboExtProperty;

public class MemberApiImpl implements MemberApi {
	private static final Logger logger = LoggerFactory.getLogger("MemberApiImpl");
	
	@Autowired
	private MemberManager memberManager ;
	
	
	@Override
	@MethodLogger(isPrintArguments=true, isPrintResult =false, isHttpApi=true, isCatchException=true)
	public MemberDetail getMemberDetail(int appId, int domainId, long deviceId, long userId, int versionCode) {
		
		MemResult<MemberDetail> result = memberManager.getMemberDetail(userId);
		if( result == null || !result.isSuccess() ){
			logger.error("getMemberDetail failed!  userId={}, Result={}", userId, JSON.toJSONString(result));
			if( result != null ){
				int code = result.getErrorCode() ;
				if( MemberReturnCode.MEMBER_NOT_FOUND.getCode() == code ){
					DubboExtProperty.setErrorCode(MemberReturnCode.MEMBER_NOT_FOUND);
				}
			}
			return null;
		}
		MemberDetail detail = result.getValue();
		return detail;
	}

	@Override
	@MethodLogger(isPrintArguments=true, isPrintResult =false, isHttpApi=true, isCatchException=true)
	public MemberPurchauseDetail getMemberPurchuseDetail(int appId, int domainId, long deviceId, long userId, int versionCode) {
		MemResult<MemberPurchauseDetail> result = memberManager.getMemberPurchuseDetail(userId);
		if( result == null || !result.isSuccess() ){
			logger.error("getMemberDetail failed!  userId={}, Result={}", userId, JSON.toJSONString(result));
			return null;
		}
		MemberPurchauseDetail detail = result.getValue();
		return detail;
	}

}
