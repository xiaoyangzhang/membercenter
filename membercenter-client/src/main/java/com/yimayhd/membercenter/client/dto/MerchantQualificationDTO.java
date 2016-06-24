package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;

public class MerchantQualificationDTO implements Serializable {
	
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -4992448560122349158L;
	private MerchantQualificationDO merchantQualification = new MerchantQualificationDO();
	public MerchantQualificationDO getMerchantQualification() {
		return merchantQualification;
	}
	public void setMerchantQualification(
			MerchantQualificationDO merchantQualification) {
		this.merchantQualification = merchantQualification;
	}
	
//	private long id;
//
//    private long merchantCategoryId;
//
//    private long qulificationId;
//
//    private long sellerId;
//
//    private String content;
//
//    private Date gmtCreated;
//
//    private Date gmtModified;
//    private int domainId;
//    private int status;
//    
//    public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public int getDomainId() {
//		return domainId;
//	}
//
//	public void setDomainId(int domainId) {
//		this.domainId = domainId;
//	}
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getMerchantCategoryId() {
//        return merchantCategoryId;
//    }
//
//    public void setMerchantCategoryId(long merchantCategoryId) {
//        this.merchantCategoryId = merchantCategoryId;
//    }
//
//    public long getQulificationId() {
//        return qulificationId;
//    }
//
//    public void setQulificationId(long qulificationId) {
//        this.qulificationId = qulificationId;
//    }
//
//    public long getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(long sellerId) {
//        this.sellerId = sellerId;
//    }
//
//   
//
//    public Date getGmtCreated() {
//        return gmtCreated;
//    }
//
//    public void setGmtCreated(Date gmtCreated) {
//        this.gmtCreated = gmtCreated;
//    }
//
//    public Date getGmtModified() {
//        return gmtModified;
//    }
//
//    public void setGmtModified(Date gmtModified) {
//        this.gmtModified = gmtModified;
//    }
}