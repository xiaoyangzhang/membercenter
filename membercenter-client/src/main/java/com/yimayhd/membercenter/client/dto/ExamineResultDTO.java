/*
 * FileName: ExamineResultDTO.java
 * Author:   liubb
 * Date:     2016年4月5日 下午7:18:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

import com.yimayhd.membercenter.enums.ExamineStatus;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核处理结果〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineResultDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = 9063311785078044086L;

    // 审核状态
    private ExamineStatus status;
    // 审核信息
    private String dealMes;

    public ExamineStatus getStatus() {
        return status;
    }

    public void setStatus(ExamineStatus status) {
        this.status = status;
    }

    public String getDealMes() {
        return dealMes;
    }

    public void setDealMes(String dealMes) {
        this.dealMes = dealMes;
    }

}
