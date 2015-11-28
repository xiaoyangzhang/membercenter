package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

/**
 * Created by menhaihao on 2014/6/18.
 *
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 7378807577314788084L;
    protected int pageNo = 1;
    protected int pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getOldPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        int start =  (pageNo - 1) * pageSize;
        if( start < 0 ){
        	start = 0 ;
        }
        return start ;
    }
}
