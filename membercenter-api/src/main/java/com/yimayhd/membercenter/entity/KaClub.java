package com.yimayhd.membercenter.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/14.
 */
@Description("大咖俱乐部信息")
public class KaClub implements Serializable {

    private static final long serialVersionUID = 1L;

    @Description("俱乐部id")
    public long clubId;

    @Description("俱乐部名称")
    public String clubName;

    @Description("俱乐部图片")
    public String clubImg;
}
