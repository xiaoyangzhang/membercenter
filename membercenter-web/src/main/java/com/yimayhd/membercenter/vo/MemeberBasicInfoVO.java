package com.yimayhd.membercenter.vo;

import java.io.Serializable;

public class MemeberBasicInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6400387427455175346L;
	// 用户Id
	private Long userId;
	// 商户Id
	private Long merchantId;
	//
	private String openId;
	// 手机号码
	private String phone;
	// 会员名称
	private String name;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
