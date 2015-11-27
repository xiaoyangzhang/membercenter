package com.yimayhd.membercenter.client.query;

import com.yimayhd.user.client.query.PageQuery;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class TravelkaPageQuery extends PageQuery {

    private List<Long> ids;

    private String orderbyCol;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getOrderbyCol() {
        return orderbyCol;
    }

    public void setOrderbyCol(String orderbyCol) {
        this.orderbyCol = orderbyCol;
    }
}
