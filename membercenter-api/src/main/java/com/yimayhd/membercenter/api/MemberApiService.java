package com.yimayhd.membercenter.api;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.define.CommonParameter;

/**
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:24:11
 */

@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = com.yimayhd.membercenter.api.PrivilegeApi.class, owner = "徐胜强")
public interface MemberApiService {

	
	public void getMemberDetail(
			@ApiAutowired(CommonParameter.applicationId) int appId,
			@ApiAutowired(CommonParameter.domainId) int domainId, 
			@ApiAutowired(CommonParameter.deviceId) long deviceId,
			@ApiAutowired(CommonParameter.userId) long userId,
			@ApiAutowired(CommonParameter.versionCode) int versionCode);
}
