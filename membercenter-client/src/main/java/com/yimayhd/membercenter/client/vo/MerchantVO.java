package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * Created by root on 15-11-25.
 */
public class MerchantVO implements Serializable {

    private String openId;

    private Long merchantId;

    private String mobile;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MerchantVO{");
        sb.append("openId='").append(openId).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
