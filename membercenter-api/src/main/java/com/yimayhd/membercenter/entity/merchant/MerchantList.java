/*
 * FileName: MerchantList.java
 * Author:   liubb
 * Date:     2016年3月21日 下午7:15:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.merchant;

import java.io.Serializable;
import java.util.List;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈店铺基本信息列表〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("店铺基本信息列表")
public class MerchantList implements Serializable {

    /**
     */
    private static final long serialVersionUID = 8062194917403775330L;
    
    @Description("当前页码")
    public int pageNo;

    @Description("是否有下一页")
    public boolean hasNext;

    @Description("店铺基本信息列表")
    public List<MerchantInfo> merchantList;

}
