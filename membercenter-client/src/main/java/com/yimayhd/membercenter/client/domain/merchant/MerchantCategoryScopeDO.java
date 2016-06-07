package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MerchantCategoryScopeDO implements Serializable {
    private long id;

    private long merchantCategoryId;

    private long businessScopeId;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private int domainId;
    private Set<Long> idSet ;
    
    public Set<Long> getIdSet() {
		return idSet;
	}

	public void setIdSet(Set<Long> idSet) {
		this.idSet = idSet;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
    private static final long serialVersionUID = 1L;

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