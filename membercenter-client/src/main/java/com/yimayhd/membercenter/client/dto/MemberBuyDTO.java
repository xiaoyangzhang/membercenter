package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

public class MemberBuyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String outerId;
	private int outerType;
	private int period;
	private long buyerId;
	private long sellerId;

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public int getOuterType() {
		return outerType;
	}

	public void setOuterType(int outerType) {
		this.outerType = outerType;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

}
