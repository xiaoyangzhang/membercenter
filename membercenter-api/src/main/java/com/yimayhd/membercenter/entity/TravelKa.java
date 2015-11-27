package com.yimayhd.membercenter.entity;



import com.yimayhd.user.entity.UserInfo;
import net.pocrd.annotation.Description;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
@Description("旅游咖信息")
public class TravelKa implements Serializable {


    @Description("id")
    public long id; //

    @Description("userId")
    public long userId; //

    @Description("用户对象实体")
    public UserInfo userInfo;

    @Description("旅游咖能力")
    public List<Ability> abilities;

    @Description("旅游咖俱乐部信息")
    public TravelKaClub travelKaClub;

    @Description("服务内容")
    public String serviceContent; // 服务内容

    @Description("职业id")
    public long occupationId; // 职业id

    @Description("背景图片")
    public String backgroundImg; // 背景图片

    @Description("是否删除 0否 1是")
    public String isDel; // 是否删除 0否 1是

    @Description("是否是大咖 0否 1是")
    public String isTravelKa; //是否是大咖 0否 1是

    @Description("改修时间")
    public long gmtModified; // 改修时间

    @Description("创建时间")
    public long gmtCreated; // 创建时间

    @Description("真实身份验证 0-否 1-是")
    public String identityValidated; // 真实身份验证 0-否 1-是

    @Description("电话号码验证  0-否 1-是")
    public String mobileValidated; // 电话号码验证  0-否 1-是

    @Description("职业验证 0-否 1-是")
    public String occupationValidated; // 职业验证 0-否 1-是



}
