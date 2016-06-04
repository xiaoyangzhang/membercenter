package com.yimayhd.membercenter.client.query;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: BusinessScopeQueryDTO
* @Description: 管理商家的经营范围
* @author zhangxy
* @date 2016年6月4日 上午11:32:59
*
 */
public class BusinessScopeQueryDTO {

	protected  final Logger log = LoggerFactory.getLogger(getClass());
	private int domainId;
	private int status;
	private long merchantCategoryId;
	private Set<Long> idSet = new HashSet<Long>();
	private long sellerId;
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getMerchantCategoryId() {
		return merchantCategoryId;
	}
	public void setMerchantCategoryId(long merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}
	public Set<Long> getIdSet() {
		return idSet;
	}
	public void setIdSet(Set<Long> idSet) {
		this.idSet = idSet;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	
}
