package com.yimayhd.membercenter.client.query;

/**
 * Created by xushubing on 2016/6/8.
 */
public class MenuListQuery extends PageQuery {
    private static final long serialVersionUID = 7051464358097159290L;
    private Integer id;//menu id 非必填 查询使用
    private String name;//menu name 非必填查询使用

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public MenuListQuery setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuListQuery setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + ",MenuListQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
