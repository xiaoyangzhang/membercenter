package com.yimayhd.membercenter.client.enums;

public enum MemberStatus {
	ACTIVE(10, "可用的"),
	EXPIRED(20, "已过期")
	;
	
	private int status ;
	private String desc ;
	private MemberStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public static MemberStatus get(int status){
		for( MemberStatus memberRecordOutStatus : MemberStatus.values() ){
			if( memberRecordOutStatus.status == status ){
				return memberRecordOutStatus ;
			}
		}
		return null ;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
