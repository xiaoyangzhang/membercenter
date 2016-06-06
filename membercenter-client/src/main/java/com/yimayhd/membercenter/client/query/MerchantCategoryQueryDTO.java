package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: MerchantCategoryQueryDTO
* @Description: 查询商家的身份
* @author zhangxy
* @date 2016年6月4日 上午11:32:59
*
 */
public class MerchantCategoryQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 2459584914091043354L;
	protected  final Logger log = LoggerFactory.getLogger(getClass());
	private int domainId;
	private long id;
	private int status;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
