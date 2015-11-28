package com.yimayhd.membercenter.entity;

import java.io.Serializable;

import net.pocrd.annotation.Description;

@Description("特权信息")
public class PrivilegeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5676960091177297389L;

	@Description("特权名称")
    public String title;

	@Description("特权小logo")
    public String imageUrl;

	@Description("特权展示图片")
	public String imageShowUrl;

    @Description("特权描述信息")
    public String description;
}
