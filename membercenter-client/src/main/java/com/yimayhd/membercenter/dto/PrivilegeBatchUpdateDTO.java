package com.yimayhd.membercenter.dto;

import java.io.Serializable;
import java.util.List;

public class PrivilegeBatchUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489462692396668795L;

	private int status;
	
	private List<Long> ids;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
}
