package com.yimayhd.membercenter.client.domain.examine;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈审核对象〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineDO implements Serializable {
    
    /**
     */
    private static final long serialVersionUID = 8440868133724462009L;
    
    private long id;
    
    private int domainId;
    //卖家ID
    private long sellerId;
    //店铺名称
    private String sellerName;
    //店铺负责人姓名
    private String principalName;
    //销售范围
    private String saleScope;
    //负责人手机号码
    private String telNum;
    //图片地址
    private String picturesUrl;
    //技能
    private String certificate;
    //扩展信息
    private String feature;
    //店铺类型
    private int type;
    //审核状态
    private int statues;
    //创建时间
    private Date gmtCreated;
    //更新时间
    private Date gmtModified;
    
    //审核信息
    private String examineMes;
    //审批者ID
    private long reviewerId;
    private long merchantCategoryId;//商家类目
    private int isDirectSale;//是否直营
    private long busiType;
    public long getBusiType() {
        return busiType;
    }

    public void setBusiType(long busiType) {
        this.busiType = busiType;
    }

    public int getIsDirectSale() {
		return isDirectSale;
	}

	public void setIsDirectSale(int isDirectSale) {
		this.isDirectSale = isDirectSale;
	}

	public long getMerchantCategoryId() {
		return merchantCategoryId;
	}

	public void setMerchantCategoryId(long merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getSaleScope() {
        return saleScope;
    }

    public void setSaleScope(String saleScope) {
        this.saleScope = saleScope;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getPicturesUrl() {
        return picturesUrl;
    }

    public void setPicturesUrl(String picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatues() {
        return statues;
    }

    public void setStatues(int statues) {
        this.statues = statues;
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

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getExamineMes() {
        return examineMes;
    }

    public void setExamineMes(String examineMes) {
        this.examineMes = examineMes;
    }

    public long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
    }
    
}