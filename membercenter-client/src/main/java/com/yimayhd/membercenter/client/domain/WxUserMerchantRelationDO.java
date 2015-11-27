package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信客户商家关系表
 * @table wx_user_merchant_relation
 * @author hdh
 **/
public class WxUserMerchantRelationDO implements Serializable{

    private static final long serialVersionUID = 1L;


    private long id; // 主键

    private String openId; // 微信openId

    private long merchantId; // 商家id

    private long userId; // 用户id,user的id

    private Date gmtCreated; //

    private Date gmtModified; //


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WxUserMerchantRelationDO{");
        sb.append("id=").append(id);
        sb.append(", openId='").append(openId).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", userId=").append(userId);
        sb.append(", gmtCreated=").append(gmtCreated);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append('}');
        return sb.toString();
    }
}