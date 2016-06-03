package com.yimayhd.membercenter.client.domain.draft;

import java.io.Serializable;
import java.util.Date;

public class DraftDO implements Serializable{

	/**
	 * serialVersionUID = -638270932104725839L;
	 */
	private static final long serialVersionUID = -638270932104725839L;
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 草稿名称
	 */
	private String draftName;
	
	/**
	 * 账户id（商户或用户）
	 */
	private Long accountId;

	/**
	 * 子类型名称
	 */
	private String subTypeName;
	
	/**
	 * 创建时间
	 */
	private Date gmtCreated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDraftName() {
		return draftName;
	}

	public void setDraftName(String draftName) {
		this.draftName = draftName;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
}
