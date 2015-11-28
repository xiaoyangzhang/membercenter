package com.yimayhd.membercenter.client.enums;

public enum MemberType {
	VIP(10, "vip")
	;
	
	private int type ;
	private String desc ;
	private MemberType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public static MemberType get(int type){
		for( MemberType memberRecordOutType : MemberType.values() ){
			if( memberRecordOutType.type == type ){
				return memberRecordOutType ;
			}
		}
		return null ;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
