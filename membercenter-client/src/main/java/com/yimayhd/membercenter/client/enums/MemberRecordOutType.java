package com.yimayhd.membercenter.client.enums;

public enum MemberRecordOutType {
	BUY(10, "购买会员")
	;
	
	private int type ;
	private String desc ;
	private MemberRecordOutType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public static MemberRecordOutType get(int type){
		for( MemberRecordOutType memberRecordOutType : MemberRecordOutType.values() ){
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
