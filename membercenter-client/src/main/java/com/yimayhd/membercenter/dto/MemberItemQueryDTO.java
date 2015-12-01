package com.yimayhd.membercenter.dto;

import java.util.List;

public class MemberItemQueryDTO {
	private static final long serialVersionUID = 1L;
	private List<Long> itemIds;

	public List<Long> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Long> itemIds) {
		this.itemIds = itemIds;
	}

}
