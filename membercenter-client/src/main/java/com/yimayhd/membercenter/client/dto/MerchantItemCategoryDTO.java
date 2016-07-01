package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: MerchantItemCategoryDTO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhangxy
* @date 2016年6月30日 下午4:33:32
*
 */
public class MerchantItemCategoryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8767442903830418832L;
	private int domainId;
	private int status;
	private long sellerId;
	private long categoryId;
	private List<Long> idList;
	private long examineId;
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
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public List<Long> getIdList() {
		return idList;
	}
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
	public long getExamineId() {
		return examineId;
	}
	public void setExamineId(long examineId) {
		this.examineId = examineId;
	}
	
	
}
