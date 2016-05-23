package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;

public class CategoryQualificationDO implements Serializable {
    private long id;

    private long merchantCategoryId;

    private long qulificationId;

    private byte required;

    private int serialNo;

    private int status;

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

    public byte getRequired() {
        return required;
    }

    public void setRequired(byte required) {
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