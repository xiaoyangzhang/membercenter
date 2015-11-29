package com.yimayhd.membercenter.client.enums;

public enum MemberPrivilegeStatus {
	OFFLINE(0, "下架"),
	ONLINE(10, "上线"),
	;
	
	private int status ;
	private String desc ;
	private MemberPrivilegeStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public static MemberPrivilegeStatus get(int status){
		for( MemberPrivilegeStatus memberRecordOutStatus : MemberPrivilegeStatus.values() ){
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
