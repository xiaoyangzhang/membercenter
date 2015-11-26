package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @table member_firehose
 * @author houdh
 **/
public class MemberFirehoseDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id; // 

    private long orderId; // 订单ID

    private String isDel; // 是否删除 0否 1是

    private long userId; // userId

    private long duration; // 时长

    private Date gmtModified; //

    private Date gmtCreated; // 

    private String version; // 


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setOrderId(long orderId){
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
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

    public void setDuration(long duration){
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
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