package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

/**
 * 
 * @author liuxp
 *
 */
public class PageJIUXIUQuery implements Serializable {
    /**
	 * serialVersionUID = -5408080046722594154L;
	 */
	private static final long serialVersionUID = -5408080046722594154L;
	
	protected int page = 1;
    protected int pageSize = 8;
    /**
     * 如果设置这个为true，就不会返回总数，
     * 返回的list的数量会多一个，用于判断时候还有下一页 比如要15个，会给16个，如果不够16个，说明没有下一页
     * */
    protected boolean hasNextMod = false;

    protected boolean needCount = false;
    
    protected int startRow;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0) {
        	page = 1;
        }
        this.page = page;
    }

    public void setHasNextMod(boolean hasNextMod) {
        this.hasNextMod = hasNextMod;
    }

    public boolean isHasNextMod() {
        return hasNextMod;
    }

    public boolean isNeedCount() {
        return needCount && !hasNextMod;
    }

    public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    public int getPageSize() {
        if (hasNextMod) {
            return pageSize + 1;
        }
        return pageSize;
    }

    public int getOldPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return (page - 1) * pageSize;
    }

    public void setStartRow(int startRow) {
        this.startRow = (page - 1) * pageSize;
    }
    
    @Override
    public String toString() {
        return "PageQuery{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", hasNextMod=" + hasNextMod +
                ", needCount=" + needCount +
                '}';
    }
}
