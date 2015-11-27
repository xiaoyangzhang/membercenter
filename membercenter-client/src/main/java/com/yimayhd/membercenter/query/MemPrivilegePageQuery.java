package com.yimayhd.membercenter.query;

public class MemPrivilegePageQuery extends PageQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073468322531644588L;

	private String title;
	
	private int status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
