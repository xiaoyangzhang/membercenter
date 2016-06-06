package com.yimayhd.membercenter.enums;

/**
 * 草稿箱枚举
 * @author liuxp
 *
 */
public enum DraftEnum {
	
	/**
	 * 商品类型，shangpin
	 */
	ITEM("商品", 1),
	
	/**
	 * 资源类型，ziyuan
	 */
	RESOURCE("资源", 2);
	
	private String name;
	private int value;
	
	DraftEnum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static DraftEnum getByName(String name) {
	    if(name ==null) {
	        return null;
	    }
	    for(DraftEnum draftEnum : values()) {
	        if (draftEnum.name.equals(name)) {
	            return draftEnum;
	        }
	    }
	    return null;
	}
	
	public static DraftEnum getByValue(int value) {
	    if(value == 0) {
	        return null;
	    }
	    for(DraftEnum draftEnum : values()) {
	        if (draftEnum.value==value) {
	            return draftEnum;
	        }
	    }
	    return null;
	}
	 
	 
}
