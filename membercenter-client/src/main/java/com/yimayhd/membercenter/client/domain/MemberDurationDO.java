package com.yimayhd.membercenter.client.domain;

import java.util.Date;

/**
 * 
 * @table member_duration
 * @author czf
 **/
public class MemberDurationDO{

    private static final long serialVersionUID = 1L;


    private long id; // 

    private long beginTime; // 开始时间

    private long endTime; // 结束时间

    private String isDel; // 是否删除 0否 1是

    private long userId; // 用户id

    private Date gmtModified; //

    private Date gmtCreated; // 

    private String version; // 


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setBeginTime(long beginTime){
        this.beginTime = beginTime;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setIsDel(String isDel){
        this.isDel = isDel;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtCreated(Date gmtCreated){
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

}