package com.yimayhd.membercenter.utils;

public enum MixPolicyEnum {
	SIMPLE(1,"simple");
	
	private int id;
	private String desc;
	
	private MixPolicyEnum(int id,String desc){
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
