package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * Created by root on 15-11-25.
 */
public class MerchantVO extends MerchantBaseQueryVO implements Serializable {

    private String openId;

    private Long userId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MerchantVO{");
        sb.append("openId='").append(openId).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
