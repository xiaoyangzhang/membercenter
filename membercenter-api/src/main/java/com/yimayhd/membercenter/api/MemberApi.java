package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.entity.member.MemberDetail;
import com.yimayhd.membercenter.entity.member.MemberPurchauseDetail;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.DesignedErrorCode;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

/**
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:24:11
 */

@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = MemberReturnCode.class, owner = "wuzf@yimayholiday.com")
public interface MemberApi {

	@HttpApi(name = "membercenter.getMemberDetail", desc = "获取会员详情", security = SecurityType.None, owner = "wuzf@yimayholiday.com")
	@DesignedErrorCode({MemberReturnCode.MEMBER_NOT_FOUND_C})
	public MemberDetail getMemberDetail(
			@ApiAutowired(CommonParameter.applicationId) int appId,
			@ApiAutowired(CommonParameter.domainId) int domainId, 
			@ApiAutowired(CommonParameter.deviceId) long deviceId,
			@ApiAutowired(CommonParameter.userId) long userId,
			@ApiAutowired(CommonParameter.versionCode) int versionCode);
	
	@HttpApi(name = "membercenter.getMemberPurchuseDetail", desc = "获取会员购买详情页信息", security = SecurityType.None, owner = "wuzf@yimayholiday.com")
	public MemberPurchauseDetail getMemberPurchuseDetail(
			@ApiAutowired(CommonParameter.applicationId) int appId,
			@ApiAutowired(CommonParameter.domainId) int domainId, 
			@ApiAutowired(CommonParameter.deviceId) long deviceId,
			@ApiAutowired(CommonParameter.userId) long userId,
			@ApiAutowired(CommonParameter.versionCode) int versionCode);
}
