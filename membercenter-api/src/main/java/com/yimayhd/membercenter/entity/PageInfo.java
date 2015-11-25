package com.yimayhd.membercenter.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

@Description("分页信息")
public class PageInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("页码")
    public int pageNo;
    @Description("每页大小")
    public int pageSize;
	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	} 
}
