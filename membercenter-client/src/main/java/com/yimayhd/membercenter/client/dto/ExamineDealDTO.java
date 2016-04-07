/*
 * FileName: ExamineDealDTO.java
 * Author:   liubb
 * Date:     2016年3月26日 下午6:12:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

import com.yimayhd.membercenter.client.query.InfoQueryDTO;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核结果〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineDealDTO extends InfoQueryDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = 8936935883885138415L;

    //记录ID
    private long id;
    // 是否审核通过
    private boolean checkIsOk;
    // 审核意见
    private String examineMes;
    //审批者ID
    private long reviewerId;

    public boolean isCheckIsOk() {
        return checkIsOk;
    }

    public void setCheckIsOk(boolean checkIsOk) {
        this.checkIsOk = checkIsOk;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
