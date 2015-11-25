package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家基本信息表
 * @table base_merchant
 * @author hdh
 **/
public class BaseMerchantDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id; // 主键

    private long userId; // UIC的user的id

    private String name; // 名称

    private String address; // 地址

    private String contracter; // 联系人

    private String contracterPhone; // 联系人电话

    private String handleName; // 办理人

    private Date handleGmtCreate; // 办理时间

    private String status; // 商家状态

    private String remark; // 备注

    private Date gmtCreate; // 记录创建时间

    private Date gmtUpdate; // 记录更新时间


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setContracter(String contracter){
        this.contracter = contracter;
    }

    public String getContracter() {
        return contracter;
    }

    public void setContracterPhone(String contracterPhone){
        this.contracterPhone = contracterPhone;
    }

    public String getContracterPhone() {
        return contracterPhone;
    }

    public void setHandleName(String handleName){
        this.handleName = handleName;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleGmtCreate(Date handleGmtCreate){
        this.handleGmtCreate = handleGmtCreate;
    }

    public Date getHandleGmtCreate() {
        return handleGmtCreate;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtUpdate(Date gmtUpdate){
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

}