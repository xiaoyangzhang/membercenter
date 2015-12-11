package com.yimayhd.membercenter.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1782483298710673233L;

	// 用户Id
	private Long userId;
	// 性别 1:男,2:女
	private Integer gender;
	// 生日的年份
	private String birthYear;
	// 生日的月份
	private String birthMonth;

	// 生日的日
	private String birthDay;
	// 手机号码
	private String phone;

	private String name;

	private Integer cityCode;

	private Integer provinceCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

}
