/*
 * FileName: ExamineSubmitDTO.java
 * Author:   liubb
 * Date:     2016年4月6日 下午3:22:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核信息提交对象〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineSubmitDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -4918469849825424553L;

    // 页码
    private int pageNo;
    // 基本信息
    private ExamineInfoDTO examineInfoDTO;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public ExamineInfoDTO getExamineInfoDTO() {
        return examineInfoDTO;
    }

    public void setExamineInfoDTO(ExamineInfoDTO examineInfoDTO) {
        this.examineInfoDTO = examineInfoDTO;
    }

}
