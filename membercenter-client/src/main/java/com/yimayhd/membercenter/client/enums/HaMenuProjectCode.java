package com.yimayhd.membercenter.client.enums;

/**
 * Created by xushubing on 2016/6/12.
 */
public enum HaMenuProjectCode {
    PALACE(1, "运营后台"),
    SEELER(2, "商家后台");

    private int code;
    private String desc;

    HaMenuProjectCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
