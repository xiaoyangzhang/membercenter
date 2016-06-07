package com.yimayhd.membercenter.client.query;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: QualificationQueryDTO
* @Description: 管理商家的资质
* @author zhangxy
* @date 2016年6月4日 上午11:32:59
*
 */
public class QualificationQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1642895241681795224L;
	protected  final Logger log = LoggerFactory.getLogger(getClass());
	private int domainId;
	private long sellerId;
	private long merchantCategoryId;
	private int status;
	private int directSale;
	private long businessScopeId;
	private Set<Long> idSet = new HashSet<Long>();
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public long getMerchantCategoryId() {
		return merchantCategoryId;
	}
	public void setMerchantCategoryId(long merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDirectSale() {
		return directSale;
	}
	public void setDirectSale(int directSale) {
		this.directSale = directSale;
	}
	public long getBusinessScopeId() {
		return businessScopeId;
	}
	public void setBusinessScopeId(long businessScopeId) {
		this.businessScopeId = businessScopeId;
	}
	public Set<Long> getIdSet() {
		return idSet;
	}
	public void setIdSet(Set<Long> idSet) {
		this.idSet = idSet;
	} 
	
}
