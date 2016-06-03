package com.yimayhd.membercenter.enums;
/**
 * 
* @ClassName: MerchantCategoryType
* @Description: 店铺身份
* @author zhangxy
* @date 2016年6月3日 上午9:16:52
*
 */
public enum MerchantCategoryType {

	HOME_HEAD_AGENCY("国内社总社",12),
	HOME_BRANCH_AGENCY("国内社分社",13),
	BROAD_HEAD_AGENCY("处境社总社",14),
	BROAD_BRANCH_AGENCY("处境社分社",15),
	HOTEL_AGENT("酒店代理商",5),
	HOTEL_GROUP("集团酒店/连锁酒店",3),
	MONOMER_HOTEL("单体酒店",4),
	INDIVIDUAL_BUSINESS("个体工商户",17),
	PARTNERSHIP_BUSINESS("合伙企业",18),
	OTHER_ORG("其他组织团体",19),
	SCENIC_SPOT("景区",6),
	AMUSEMENT_PARK("游乐园",7),
	TICKET_AGENT("门票代理商",8),
	SCENIC_SPOT_GROUP("景区联盟",9),
	COMPANY("公司",16),
	TOUR_BUSINESS_SERVICE("旅游商务服务公司",2),
	TOURISM_PROMOTION("旅游局推广商家",10);
	private String name;
	private int subType;
	 MerchantCategoryType(String name, int subType) {
		this.name = name;
		this.subType = subType;
	}
	public String getName() {
		return name;
	}
	public int getSubType() {
		return subType;
	}
	
	
}
