package com.yimayhd.membercenter.client.domain.merchant;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QualificationDO implements Serializable {
	private static final long serialVersionUID = 1L;
    private long id;

    private String title;

    private int type;

    private String tip;

    private int status;

    private Date gmtCreated;

    private Date gmtModified;
    private int num; 
    private int domainId;
    private List<Long> idList;
    private List<CategoryQualificationDO> categoryQualificationList;
    private boolean required;
    private String overallNote;//全局备注
    
    public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getOverallNote() {
		return overallNote;
	}

	public void setOverallNote(String overallNote) {
		this.overallNote = overallNote;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public List<CategoryQualificationDO> getCategoryQualificationList() {
		return categoryQualificationList;
	}

	public void setCategoryQualificationList(
			List<CategoryQualificationDO> categoryQualificationList) {
		this.categoryQualificationList = categoryQualificationList;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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