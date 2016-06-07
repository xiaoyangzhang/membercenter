package com.yimayhd.membercenter.enums;
/**
 * 
* @ClassName: ExamineCharacter
* @Description: 入驻店铺的性质
* @author zhangxy
* @date 2016年5月26日 下午8:54:51
*
 */
public enum ExamineCharacter {

	DIRECT_SALE("直营店",1),
	BOUTIQUE("专营店",2);
	
	private String name;
	private int type;
	
	 ExamineCharacter(String name, int type) {
		this.name = name;
		this.type = type;
	}
	 
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
	
}
