package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

/**
 * 草稿详细信息DO
 * @author liuxp
 *
 */
public class DraftDetailDTO implements Serializable{

	/**
	 * serialVersionUID = -8597477579983510912L;
	 */
	private static final long serialVersionUID = -8597477579983510912L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * json数据字符串
	 */
	private String JSONStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJSONStr() {
		return JSONStr;
	}

	public void setJSONStr(String jSONStr) {
		JSONStr = jSONStr;
	}
}
