package com.yimayhd.membercenter.client.enums;

/**
 * 
 * @author wzf
 *
 */
public enum HaMenuRequestType {
    ALL(0,"不限"),
    GET(1,"GET"),
    POST(2,"POST"),
    PUT(3,"PUT"),
    DELETE(4,"DELETE")
    ;
	

    private int type;
    private String desc ;

    HaMenuRequestType(int type, String desc) {
        this.type = type;
        this.desc = desc;
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
