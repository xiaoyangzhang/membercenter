package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfoPageList;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = MemberReturnCode.class, owner = "徐胜强")
public interface PrivilegeApi {

	@HttpApi(name = "membercenter.getPrivilegeInfoPageList", desc = "返回会员特权列表,此方法已废弃，使用getMemberDetail和getMemberPurchuseDetail的方法", security = SecurityType.None, owner = "徐胜强")
	public PrivilegeInfoPageList getPrivilegeInfoPageList(
			@ApiAutowired(CommonParameter.applicationId) int appId,
			@ApiAutowired(CommonParameter.domainId) int domainId, 
			@ApiAutowired(CommonParameter.deviceId) long deviceId,
			@ApiAutowired(CommonParameter.userId) long userId,
			@ApiAutowired(CommonParameter.versionCode) int versionCode,
			@ApiParameter(required = true, name = "pageInfo", desc = "分页信息") PageInfo pageInfo);
}
