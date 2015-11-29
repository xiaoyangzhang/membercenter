package com.yimayhd.membercenter.entity.member;

import java.util.List;

import com.yimayhd.membercenter.entity.PrivilegeInfo;

import net.pocrd.annotation.Description;

/**
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:28:44
 */
@Description("会员购买详情页")
public class MemberPurchauseDetail {
	@Description("会员商品列表")
	public List<MemeberItem> memeberItems;
	
	@Description("特权列表")
	public List<PrivilegeInfo> privilegeInfos;
	
}
