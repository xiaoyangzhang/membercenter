package com.yimayhd.membercenter.enums;
/**
 * 
* @ClassName: CertificateType
* @Description: 证件类型
* @author zhangxy
* @date 2016年6月2日 下午3:16:43
*
 */
public enum CertificateType {

	IDCARD("身份证","0"),
	CAR_LICENSE("驾驶证","1"),
	PASSPORT("护照","2"),
	GUIDE_LICENSE("导游证","3");
	
	private String name;
	private String type;
	 CertificateType(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
	
}
