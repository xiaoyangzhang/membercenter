package com.yimayhd.membercenter.entity.member;

import net.pocrd.annotation.Description;

/**   
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:50:42 
 */
@Description("优惠信息")
public class MemeberDiscount {

	@Description("商品id")
	public long itemId ;
	
	@Description("商品图片")
	public String itemPic ;
	
	@Description("商品标题")
	public String itemTitle ;
	
	@Description("购买时间")
	public long buyTime ;
	
}
