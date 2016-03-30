/*
 * FileName: TalentInfoDO.java
 * Author:   liubb
 * Date:     2016年3月15日 下午5:01:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.domain.talent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yimayhd.membercenter.client.domain.CertificatesDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基本信息详情〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentInfoDO implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2425786854389021023L;
    
    private long id;//会员Id
    
    private String avatar; // 用户头像

    private String nickName; // 昵称
    
    private String reallyName; //真实姓名

    private int gender; // 性别  1-男 2-女
    
    private String serveDesc;// 服务描述
    
    private long serveCount;//服务次数
    
    private String city;//城市
    
    private int cityCode;//城市编码
    
    private List<String> pictures;//轮播图
    
    private List<CertificatesDO> certificates;//认证信息
    
    private boolean type;//是否是大V
    
    private String telNum;// 手机号码
    
    private Date birthday;//生日

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getServeDesc() {
        return serveDesc;
    }

    public void setServeDesc(String serveDesc) {
        this.serveDesc = serveDesc;
    }

    public long getServeCount() {
        return serveCount;
    }

    public void setServeCount(long serveCount) {
        this.serveCount = serveCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<CertificatesDO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificatesDO> certificates) {
        this.certificates = certificates;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getReallyName() {
        return reallyName;
    }

    public void setReallyName(String reallyName) {
        this.reallyName = reallyName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
}
