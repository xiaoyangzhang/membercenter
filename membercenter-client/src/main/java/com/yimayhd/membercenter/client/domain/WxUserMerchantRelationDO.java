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


    private Long id; // 主键

    private String openId; // 微信openId

    private Long merchantId; // 商家id

    private Long userId; // 用户id,user的id

    private Date gmtCreated; //

    private Date gmtModified; //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("WxUserMerchantRelationDO [id=");
		builder.append(id);
		builder.append(", openId=");
		builder.append(openId);
		builder.append(", merchantId=");
		builder.append(merchantId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", gmtCreated=");
		builder.append(gmtCreated);
		builder.append(", gmtModified=");
		builder.append(gmtModified);
		builder.append("]");
		return builder.toString();
	}


   
}