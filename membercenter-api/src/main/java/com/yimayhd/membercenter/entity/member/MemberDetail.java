package com.yimayhd.membercenter.entity.member;

import java.util.List;

import com.yimayhd.membercenter.entity.PrivilegeInfo;

import net.pocrd.annotation.Description;

/**
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:28:44
 */
@Description("会员详情")
public class MemberDetail {
	@Description("会员信息")
	public Member member;
	
	@Description("特权列表")
	public List<PrivilegeInfo> privilegeInfos;
	
	
	
}
