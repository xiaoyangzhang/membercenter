package com.yimayhd.membercenter.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/27.
 */
@Description("用户基本信息")
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 1L;

    @Description("id")
    public long id; //

    @Description("用户头像")
    public String avatar; // 用户头像

    @Description("昵称")
    public String nickname; // 昵称

    @Description("用户姓名")
    public String name; // User Name

    @Description("性别 1-未确认 2-男  3-女")
    public String gender; // 性别  1-男 2-女

    @Description("生日")
    public long birthday; // 生日

    @Description("常住地 省code")
    public int provinceCode; //  常住地 省

    @Description("常住地 市code")
    public int cityCode; //  常住地 市

    @Description("个性签名")
    public String signature; // 个性签名

    @Description("年龄")
    public int age;

    @Description("原驻地")
    public String liveStation;  //原驻地

    @Description("省名称")
    public String province;

    @Description("市名称")
    public String city;
}
