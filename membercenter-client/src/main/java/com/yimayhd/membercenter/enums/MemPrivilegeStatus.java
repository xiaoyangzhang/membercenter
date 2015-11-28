package com.yimayhd.membercenter.enums;


public enum MemPrivilegeStatus {

	AVAILABLE("有效的",10),
	INVALID("无效的",20);
    private String desc;
    private int status;

    MemPrivilegeStatus(String desc, int status) {
        this.desc = desc;
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public int getStatus() {
        return status;
    }

    public static MemPrivilegeStatus getByStatus(int status) {
        for (MemPrivilegeStatus userType : values()) {
            if (userType.getStatus() == status) {
                return userType;
            }
        }
        return null;
    }

    public static MemPrivilegeStatus getByName(String name) {
        if (name == null) {
            return null;
        }
        for (MemPrivilegeStatus userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}
