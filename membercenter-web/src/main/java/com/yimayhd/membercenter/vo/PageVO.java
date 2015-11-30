package com.yimayhd.membercenter.vo;

import java.io.Serializable;

public class PageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114536230429445060L;

	// 当前页
	private Integer currentPage;
	// 前一页
	private Integer previousPage;

	// 单个页面展示的条目大小
	private Integer pageSize;
	// 总页数
	private Integer totalPage;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
