package com.yimayhd.membercenter.client.enums;

public enum MerchantCategoryFeatureKey {
	BUSINESS_TYPE("10", "商户的business type")
	
	;
	
	private String value ;
	private String desc ;
	private MerchantCategoryFeatureKey(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
