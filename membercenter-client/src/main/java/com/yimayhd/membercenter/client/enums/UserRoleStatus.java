package com.yimayhd.membercenter.client.enums;

public enum UserRoleStatus {
	ACTIVE(1, "有效"),
	DISACTIVE(0, "废弃")
	;
	
	private int status ;
	private String desc ;
	private UserRoleStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public static UserRoleStatus get(int status){
		for( UserRoleStatus userRoleStatus : UserRoleStatus.values() ){
			if( userRoleStatus.status == status ){
				return userRoleStatus ;
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
