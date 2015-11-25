package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;

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


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setOpenId(String openId){
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setMerchantId(long merchantId){
        this.merchantId = merchantId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

}