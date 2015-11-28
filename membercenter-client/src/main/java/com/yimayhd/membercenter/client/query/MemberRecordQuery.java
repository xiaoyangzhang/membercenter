package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

public class MemberRecordQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	private String outerId;
	private int outerType;

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

}
