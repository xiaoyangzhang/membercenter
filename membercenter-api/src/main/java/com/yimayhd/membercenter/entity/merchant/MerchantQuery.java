/*
 * FileName: QueryMechant.java
 * Author:   liubb
 * Date:     2016年3月21日 下午7:22:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.merchant;

import java.io.Serializable;

import com.yimayhd.membercenter.entity.PageInfo;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈店铺列表查询〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("店铺列表查询")
public class MerchantQuery implements Serializable {

    /**
     */
    private static final long serialVersionUID = -2566162063515217675L;
    
    @Description("店铺类型")
    public String merchantType;
    
    @Description("分页查询")
    public PageInfo pageInfo;

}
