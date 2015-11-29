package com.yimayhd.membercenter.client.query;

public class MemPrivilegePageQuery extends PageQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073468322531644588L;

	private String title;
	
	private int[] statuses;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int[] getStatuses() {
		return statuses;
	}

	public void setStatuses(int[] statuses) {
		this.statuses = statuses;
	}
	public void setStatus(int status) {
		this.statuses = new int[1];
		this.statuses[0] = status ;
	}

	
}
