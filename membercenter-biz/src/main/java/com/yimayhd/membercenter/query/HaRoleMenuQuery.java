package com.yimayhd.membercenter.query;

import java.util.List;

/**
 * Created by czf on 2016/3/8.
 */
public class HaRoleMenuQuery {
    private List<Long> roleIdList;
    private long domain;//domain(暂时用不到)

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public long getDomain() {
        return domain;
    }

    public void setDomain(long domain) {
        this.domain = domain;
    }
}
