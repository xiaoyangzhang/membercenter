package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.membercenter.client.domain.feature.MerchantCategoryFeature;


public class MerchantCategoryDO implements Serializable {
    private long id;

    private String name;

    private String pic;

    private String description;

    private int type;

    private long parentId;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private String busiType;
    
    private String feature ;
    
    private static final long serialVersionUID = 1L;
    
    public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	private int domainId;
    
    public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

//	public String getFeatrue() {
//		return featrue;
//	}
//
//	public void setFeatrue(String featrue) {
//		this.featrue = featrue;
//	}
	

    private MerchantCategoryFeature merchantCategoryFeature;

	public String getFeature() {
        if(merchantCategoryFeature == null) {
            return null;
        }
        return merchantCategoryFeature.getFeature();
    }

    public void setFeature(String feature) {
        this.merchantCategoryFeature = new MerchantCategoryFeature(feature);
    }

    public MerchantCategoryFeature getMerchantCategoryFeature() {
        return merchantCategoryFeature;
    }

    public void setMerchantCategoryFeature(MerchantCategoryFeature merchantCategoryFeature) {
        this.merchantCategoryFeature = merchantCategoryFeature;
    }
	
}