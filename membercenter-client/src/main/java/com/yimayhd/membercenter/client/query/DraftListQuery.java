package com.yimayhd.membercenter.client.query;


/**
 * 草稿箱列表查询条件
 * @author liuxp
 *
 */
public class DraftListQuery extends PageJIUXIUQuery {

	/**
	 * serialVersionUID = -2428067804226802107L;
	 */
	private static final long serialVersionUID = -2428067804226802107L;
	
	/**
	 * 主类型
	 */
	private int mainType;
	
	/**
	 * 子类型
	 */
	private int subType;
	
	/**
	 * 商户id
	 */
	private Long accountId;
	
	/**
	 * domainID
	 */
	private int domainId;

	public int getMainType() {
		return mainType;
	}

	public void setMainType(int mainType) {
		this.mainType = mainType;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
}
