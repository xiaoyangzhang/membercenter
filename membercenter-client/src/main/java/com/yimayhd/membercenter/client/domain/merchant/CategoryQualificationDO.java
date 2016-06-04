package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CategoryQualificationDO implements Serializable {
    private long id;

    private long merchantCategoryId;

    private long qulificationId;

    private boolean required;

    private int serialNo;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private int domainId;
    private long businessScopeId;
    private int isDirectSale;
    private List<Long> scopeIdsList;
    
    public List<Long> getScopeIdsList() {
		return scopeIdsList;
	}

	public void setScopeIdsList(List<Long> scopeIdsList) {
		this.scopeIdsList = scopeIdsList;
	}

	public long getBusinessScopeId() {
		return businessScopeId;
	}

	public void setBusinessScopeId(long businessScopeId) {
		this.businessScopeId = businessScopeId;
	}

	public int getIsDirectSale() {
		return isDirectSale;
	}

	public void setIsDirectSale(int isDirectSale) {
		this.isDirectSale = isDirectSale;
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

    public long getQulificationId() {
        return qulificationId;
    }

    public void setQulificationId(long qulificationId) {
        this.qulificationId = qulificationId;
    }

    public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
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