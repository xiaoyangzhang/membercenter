package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QualificationDO implements Serializable {
    private long id;

    private String title;

    private String type;

    private String tip;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;
    private int domainId;
    private List<Long> idList;
    private List<CategoryQualificationDO> categoryQualificationList;
    private int required;
    public List<CategoryQualificationDO> getCategoryQualificationList() {
		return categoryQualificationList;
	}

	public void setCategoryQualificationList(
			List<CategoryQualificationDO> categoryQualificationList) {
		this.categoryQualificationList = categoryQualificationList;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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
}