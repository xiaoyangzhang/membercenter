package com.yimayhd.membercenter.query;

import java.util.List;

/**
 * Created by czf on 2016/3/8.
 */
public class HaMenuQuery {
    private List<Long> menuIdList;
    private long domain;//domain
    private List<Integer> typeList;

    public HaMenuQuery() {
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public long getDomain() {
        return domain;
    }

    public void setDomain(long domain) {
        this.domain = domain;
    }

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }
}
