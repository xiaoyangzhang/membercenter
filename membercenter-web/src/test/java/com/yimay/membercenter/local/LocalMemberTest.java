package com.yimay.membercenter.local;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimay.membercenter.LocalBaseTest;
import com.yimayhd.membercenter.api.MemberApi;
import com.yimayhd.membercenter.entity.member.MemberDetail;

public class LocalMemberTest extends LocalBaseTest{
	@Autowired
	private MemberApi memberApi ;

	@Test
	public void test(){
		process();
	}
	
	private void process(){
		getMemberDetail();
	}
	
	private void getMemberDetail(){
		long userId = 401 ;
		int appId =1 ;
		int domainId = 1 ;
		int versionCode = 10 ;
		long deviceId= 10 ;
		MemberDetail memberDetail = memberApi.getMemberDetail(appId, domainId, deviceId, userId, versionCode);
		
		printResult(memberDetail, "getMemberDetail");
		System.err.println();
	}
}
