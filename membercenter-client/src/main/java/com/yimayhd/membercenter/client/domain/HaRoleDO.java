package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表（菜单）
 * @table ha_role
 * @author czf
 **/
public class HaRoleDO implements Serializable {

    private static final long serialVersionUID = 4253103877866042198L;

    protected long id;//主键
    private long domain; // domain
    protected Date gmtCreated;//创建时间
    protected Date gmtModified;//更新时间
    protected int status;//1-正常0-删除
    private String name; // 角色名称
    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDomain() {
        return domain;
    }

    public void setDomain(long domain) {
        this.domain = domain;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}