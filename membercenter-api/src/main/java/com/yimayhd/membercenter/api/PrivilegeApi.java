package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfoPageList;
import com.yimayhd.membercenter.errorcode.PrivilegeApiCode;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.DesignedErrorCode;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = com.yimayhd.membercenter.api.PrivilegeApi.class, owner = "徐胜强")
public interface PrivilegeApi {

	//FIXME 徐胜强 SERVER_ERROR 这个不需要暴露出来，使用MemberReturnCode这个
	@HttpApi(name = "membercenter.getTravelKaListPage", desc = "返回会员特权列表", security = SecurityType.None, owner = "徐胜强")
	@DesignedErrorCode({ PrivilegeApiCode.SERVER_ERROR_C })
	public PrivilegeInfoPageList getPrivilegeInfoPageList(
			@ApiAutowired(CommonParameter.applicationId) int appId,
			@ApiAutowired(CommonParameter.domainId) int domainId, 
			@ApiAutowired(CommonParameter.deviceId) long deviceId,
			@ApiAutowired(CommonParameter.userId) long userId,
			@ApiAutowired(CommonParameter.versionCode) int versionCode,
			@ApiParameter(required = true, name = "pageInfo", desc = "分页信息") PageInfo pageInfo);
}
