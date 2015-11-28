package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;
import java.util.Date;

public class MemberPrivilegeDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3893167158883158641L;

	/**
     * 
     */
    private long id;

    /**
     * 
     */
    private String title;

    /**
     * 图片
     */
    private String imageUrl;

    /**
     * 会员展示图片
     */
    private String imageShowUrl;

    /**
     * 
     */
    private String description;

    /**
     * 10 启用 20停用
     */
    private int status;
    
    
    private long domainId;

    /**
     * 
     */
    private Date gmtCreated;

    /**
     * 
     */
    private Date gmtModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getImageShowUrl() {
        return imageShowUrl;
    }

    public void setImageShowUrl(String imageShowUrl) {
        this.imageShowUrl = imageShowUrl == null ? null : imageShowUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
    
    
	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	@Override
	public String toString() {
		return "MemberPrivilegeDO [id=" + id + ", title=" + title + ", imageUrl=" + imageUrl + ", imageShowUrl="
				+ imageShowUrl + ", description=" + description + ", status=" + status + ", gmtCreated=" + gmtCreated
				+ ", gmtModified=" + gmtModified + "]";
	}
    
}