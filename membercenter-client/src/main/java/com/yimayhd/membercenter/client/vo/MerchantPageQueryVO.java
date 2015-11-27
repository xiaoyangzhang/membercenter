package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * Created by root on 15-11-26.
 */
public class MerchantPageQueryVO extends MerchantBaseQueryVO implements Serializable {

    private String nickName;

    private String city;

    private int pageNo = 1;

    private int pageSize;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
