package com.yimayhd.membercenter.dto;

import com.yimayhd.membercenter.client.query.PageQuery;

public class MemberDiscountQueryDTO extends PageQuery {
	private static final long serialVersionUID = 1L;
	private long buyerId;

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

}
