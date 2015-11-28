package com.yimayhd.membercenter.entity.member;

import java.io.Serializable;
import java.util.Date;

import net.pocrd.annotation.Description;

/**
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:28:44
 */
@Description("会员信息")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	@Description("id")
	public long id;

	@Description("user id")
	public long userId;

	@Description("类型")
	public String type;

	@Description("状态")
	public String status;

	@Description("会员有效期开始时间")
	public long startTime;

	@Description("会员有效期截止时间")
	public long endTime;

}
