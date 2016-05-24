package com.yimayhd.membercenter.client.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ScopeItemCategoryVO implements Serializable {
    private static final long serialVersionUID = -211033817147854437L;

    private long businessScopeId;

    private long itemCategoryId;

    public long getBusinessScopeId() {
        return businessScopeId;
    }

    public void setBusinessScopeId(long businessScopeId) {
        this.businessScopeId = businessScopeId;
    }

    public long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }
}
