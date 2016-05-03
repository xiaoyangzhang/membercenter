package com.yimayhd.membercenter.client.enums;

/**
 * Created by czf on 2016/3/8.
 */
public enum HaMenuType {
    MENU(1,"菜单"),
    URL(0,"功能");

    private int type;
    private String desc ;

    HaMenuType(int type, String desc) {
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
