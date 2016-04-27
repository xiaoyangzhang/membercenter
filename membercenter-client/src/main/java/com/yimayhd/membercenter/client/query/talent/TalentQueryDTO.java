/*
 * FileName: TalentQueryDTO.java
 * Author:   liubb
 * Date:     2016年3月19日 上午11:56:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.query.talent;

import com.yimayhd.membercenter.client.query.PageQuery;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentQueryDTO extends PageQuery {

    /**
     */
    private static final long serialVersionUID = 7556287171260201847L;
    
    //0-全部  1-全程伴游 2-包车服务 4-咨询规划
    private String tagId;
    
    //true-升序 false-降序  默认降序
    public Boolean sortType;
    
    //1-服务次数   默认服务次数
    private String sortPam;
    
    //达人搜索查询    搜索字段
    private String searchWord;
    
    private long domainId;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Boolean isSortType() {
        return sortType;
    }

    public void setSortType(Boolean sortType) {
        this.sortType = sortType;
    }

    public String getSortPam() {
        return sortPam;
    }

    public void setSortPam(String sortPam) {
        this.sortPam = sortPam;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

}
