package com.yimayhd.membercenter.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/14.
 */
@Description("能力")
public class Ability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Description("id")
    public long id; //

    @Description("能力名称")
    public String name; // 能力名称

    @Description("能力图片")
    public String img; // 能力图片
}
