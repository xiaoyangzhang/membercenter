package com.yimayhd.membercenter.entity.merchant;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.membercenter.entity.Certificates;

import net.pocrd.annotation.Description;

/**
 * Created by zhaozhaonan on 2016/03/15.
 *
 */
@Description("店铺基本信息")
public class Merchant<M> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Description("卖家Id")
    public long sellerId;
    
    @Description("店铺名称")
    public String name;

    @Description("城市code")
    public String cityCode;
    
    @Description("城市名称")
    public String city;
    
    @Description("营业时间")
    public String serviceTime;

    @Description("人均消费")
    public long avgprice;
    
    @Description("客服电话")
    public String serviceTel;
    
    @Description("店铺图标")
    public String icon;
    
    @Description("店铺背景图")
    public String backPic;
    
    @Description("经度")
    public double longitude;
    
    @Description("纬度")
    public double latitude;
    
    @Description("商铺地址")
    public String address;
    
    @Description("店铺技能类型")
    public int certificateType;
    
    @Description("店铺提供服务")
    public List<Certificates> certificates;


}
