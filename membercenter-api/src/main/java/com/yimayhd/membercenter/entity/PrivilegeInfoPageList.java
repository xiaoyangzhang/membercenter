package com.yimayhd.membercenter.entity;

import java.io.Serializable;
import java.util.List;

import net.pocrd.annotation.Description;

@Description("特权列表信息")
public class PrivilegeInfoPageList implements Serializable{

	private static final long serialVersionUID = 8081829273233753483L;

	@Description("特权列表")
	public List<PrivilegeInfo> privilegeInfoPageList;
	
	@Description("当前页码")
	public int pageNo;

    @Description("是否有下一页")
    public boolean hasNext;
}
