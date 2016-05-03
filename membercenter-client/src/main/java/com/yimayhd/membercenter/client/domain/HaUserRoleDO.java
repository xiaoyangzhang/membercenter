package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 * @table ha_user_role
 * @author czf
 **/
public class HaUserRoleDO implements Serializable {


    private static final long serialVersionUID = 4484764335558537915L;
    protected long id;//主键
    protected Date gmtCreated;//创建时间
    protected Date gmtModified;//更新时间
    protected int status;//1-正常0-删除
    private long haUserId; // 用户ID
    private long haRoleId; // 角色ID

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

    public long getHaUserId() {
        return haUserId;
    }

    public void setHaUserId(long haUserId) {
        this.haUserId = haUserId;
    }

    public long getHaRoleId() {
        return haRoleId;
    }

    public void setHaRoleId(long haRoleId) {
        this.haRoleId = haRoleId;
    }

}