package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @table member_profile
 * @author hdh
 **/
public class MemberProfileDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id; // 

    private long userId; // 

    private String serviceContent; // 服务内容

    private String identityNo; // 身份证号

    private long occupationId; // 职业id

    private String backgroundImg; // 背景图片

    private String certificatesImg; // 证件图片 

    private String isDel; // 是否删除 0否 1是

    private Date gmtModified; // 改修时间

    private Date gmtCreated; // 创建时间

    private String identityValidated; // 真实身份验证 0-否 1-是

    private String mobileValidated; // 电话号码验证  0-否 1-是

    private String occupationValidated; // 职业验证 0-否 1-是

    private int sortColumn1; // 人气旅游咖排序字段

    private int sortColumn2; // 新晋旅游咖排序字段

    private String nickname; // 昵称

    private String name; // 用户姓名


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

    public void setServiceContent(String serviceContent){
        this.serviceContent = serviceContent;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setIdentityNo(String identityNo){
        this.identityNo = identityNo;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setOccupationId(long occupationId){
        this.occupationId = occupationId;
    }

    public long getOccupationId() {
        return occupationId;
    }

    public void setBackgroundImg(String backgroundImg){
        this.backgroundImg = backgroundImg;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setCertificatesImg(String certificatesImg){
        this.certificatesImg = certificatesImg;
    }

    public String getCertificatesImg() {
        return certificatesImg;
    }

    public void setIsDel(String isDel){
        this.isDel = isDel;
    }

    public String getIsDel() {
        return isDel;
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

    public void setIdentityValidated(String identityValidated){
        this.identityValidated = identityValidated;
    }

    public String getIdentityValidated() {
        return identityValidated;
    }

    public void setMobileValidated(String mobileValidated){
        this.mobileValidated = mobileValidated;
    }

    public String getMobileValidated() {
        return mobileValidated;
    }

    public void setOccupationValidated(String occupationValidated){
        this.occupationValidated = occupationValidated;
    }

    public String getOccupationValidated() {
        return occupationValidated;
    }

    public void setSortColumn1(int sortColumn1){
        this.sortColumn1 = sortColumn1;
    }

    public int getSortColumn1() {
        return sortColumn1;
    }

    public void setSortColumn2(int sortColumn2){
        this.sortColumn2 = sortColumn2;
    }

    public int getSortColumn2() {
        return sortColumn2;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}