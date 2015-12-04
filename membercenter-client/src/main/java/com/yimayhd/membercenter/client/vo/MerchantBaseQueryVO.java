package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * Created by root on 15-11-26.
 */
public class MerchantBaseQueryVO implements Serializable{

    private String mobile;

    private Long merchantUserId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(Long merchantUserId) {
        this.merchantUserId = merchantUserId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MerchantBaseQueryVO{");
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", merchantUserId=").append(merchantUserId);
        sb.append('}');
        return sb.toString();
    }
}
