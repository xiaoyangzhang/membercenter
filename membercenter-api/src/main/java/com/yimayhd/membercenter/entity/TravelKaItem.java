package com.yimayhd.membercenter.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

/**
 * 旅游咖列表item实体信息
 * Created by Administrator on 2015/11/14.
 */
@Description("旅游咖列表item")
public class TravelKaItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Description("userId")
    public long userId;

    @Description("旅游咖能力")
    public int gender; // 性别  1-男 2-女

    @Description("用户头像")
    public String avatar; // 用户头像

    @Description("昵称")
    public String nickname; // 昵称

    @Description("省code")
    public int provinceCode; //

    @Description("市code")
    public int cityCode; //

    @Description("年龄")
    public int age;


}
