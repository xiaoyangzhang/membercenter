package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

/**
 * Created by czf on 2016/3/8.
 */
public class UserMenuQuery implements Serializable {
    private static final long serialVersionUID = -1638609193293937220L;
    private long userId; //用户ID
    private long domain; //domain

    private int projectCode;//1 运营后台;2 商家后台

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDomain() {
        return domain;
    }

    public void setDomain(long domain) {
        this.domain = domain;
    }


    public int getProjectCode() {
        return projectCode;
    }

    public UserMenuQuery setProjectCode(int projectCode) {
        this.projectCode = projectCode;
        return this;
    }
}
