package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * 草稿箱vo
 * @author liuxp
 *
 */
public class DraftVO implements Serializable{

	/**
	 * serialVersionUID = 6466567855470942094L;
	 */
	private static final long serialVersionUID = 6466567855470942094L;
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 草稿名称
	 */
	private String draftName;
	
	/**
	 * 草稿内容序列化后的对象
	 */
	private Object jsonObject;
	
	/**
	 * 子类型
	 */
	private int subType;
	
	/**
	 * 主类型
	 */
	private int mainType;
	
	/**
	 * 账户id（商户或用户）
	 */
	private Long accountId;

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

	public Object getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(Object jsonObject) {
		this.jsonObject = jsonObject;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getMainType() {
		return mainType;
	}

	public void setMainType(int mainType) {
		this.mainType = mainType;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
