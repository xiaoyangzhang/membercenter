package com.yimayhd.membercenter.entity.member;

import java.io.Serializable;

import net.pocrd.annotation.Description;

/**   
 * @author wuzhengfei
 * @date 2015年11月28日 下午5:50:42 
 */
@Description("会员商品")
public class MemeberItem  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Description("商品id")
	public long itemId ;
	
	@Description("商品图片")
	public String itemPics ;
	
	@Description("商品标题")
	public String itemTitle ;
	
	@Description("价格，单位是分")
	public long price;

	@Description("原价，单位是分")
	public long originalPrice;
	
}
