package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;
import java.util.Date;

public class MerchantCategoryScopeDTO implements Serializable {
	
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1817160303357572600L;

	private long id;

    private long merchantCategoryId;

    private long businessScopeId;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private int domainId;
    
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

    public long getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(long merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public long getBusinessScopeId() {
        return businessScopeId;
    }

    public void setBusinessScopeId(long businessScopeId) {
        this.businessScopeId = businessScopeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}