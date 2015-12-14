package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class TravelKaVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /** travelKa id*/
    private long id;

    /** userId*/
    private long userId;

    /** 姓名*/
    private String name;

    /** 昵称*/
    private String nickName;

    /** 用户角色*/
    private long options;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
