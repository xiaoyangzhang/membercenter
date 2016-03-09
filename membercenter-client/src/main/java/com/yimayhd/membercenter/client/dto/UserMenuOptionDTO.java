package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

/**
 * Created by czf on 2016/3/8.
 */
public class UserMenuOptionDTO implements Serializable {
    private static final long serialVersionUID = 6622819409051177716L;
    private boolean containUrl = false; //是否包含url权限

    public boolean isContainUrl() {
        return containUrl;
    }

    public void setContainUrl(boolean containUrl) {
        this.containUrl = containUrl;
    }
}
