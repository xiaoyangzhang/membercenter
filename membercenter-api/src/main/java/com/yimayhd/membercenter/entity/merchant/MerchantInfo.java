/*
 * FileName: MerchantInfo.java
 * Author:   liubb
 * Date:     2016年3月21日 下午7:14:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.merchant;

import java.io.Serializable;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈店铺基本信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("店铺基本信息")
public class MerchantInfo implements Serializable {

    /**
     */
    private static final long serialVersionUID = -830480935093786159L;
    
    @Description("卖家Id")
    public long sellerId;
    
    @Description("店铺名称")
    public String name;

    @Description("城市code")
    public String cityCode;
    
    @Description("城市名称")
    public String city;

    @Description("人均消费")
    public long avgprice;
    
    @Description("店铺图标")
    public String icon;

}
