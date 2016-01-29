package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;

public class AbilityDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8378411010059044437L;

	private long id;

	private String name;

	private String img;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
