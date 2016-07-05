package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;

public class MerchantItemCategoryDO implements Serializable {
    private long id;

    private long itemCategoryId;

    private long sellerId;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private int domainId;
    private int type;//商品类型
    
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

    public long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
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