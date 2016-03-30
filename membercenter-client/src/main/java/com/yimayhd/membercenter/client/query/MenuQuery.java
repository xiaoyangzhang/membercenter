package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

/**
 * Created by czf on 2016/3/30.
 */
public class MenuQuery implements Serializable{

    private static final long serialVersionUID = -6569933304600452074L;
    private long domain; //domain

    public long getDomain() {
        return domain;
    }

    public void setDomain(long domain) {
        this.domain = domain;
    }

}
