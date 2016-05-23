package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;

public class MerchantQualificationDO implements Serializable {
    private long id;

    private long merchantCategoryId;

    private long qulificationId;

    private long sellerId;

    private String value;

    private Date gmtCreated;

    private Date gmtModified;

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

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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