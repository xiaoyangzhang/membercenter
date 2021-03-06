package com.yimayhd.membercenter.query;

import java.util.List;

/**
 * Created by czf on 2016/3/8.
 */
public class HaMenuQuery {
    private List<Long> menuIdList;
    private long domain;//domain
    private List<Integer> typeList;
    private int type;
    private int projectCode;//1 运营后台;2 商家后台

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(int projectCode) {
        this.projectCode = projectCode;
    }
}
