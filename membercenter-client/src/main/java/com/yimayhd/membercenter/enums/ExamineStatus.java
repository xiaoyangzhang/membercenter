/*
 * FileName: ExaminStatus.java
 * Author:   liubb
 * Date:     2016年3月26日 下午2:59:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈审核状态〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ExamineStatus {
    
    EXAMIN_OK("审核通过", 1),
    EXAMIN_ING("审核进行中", 2),
    EXAMIN_ERROR("审核未通过", 3),
    EXAMIN_NOT_ABLE("未提交审核", 4)
    ;
    
    private String name;
    private int status;

    ExamineStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public static ExamineStatus getByStatus(int status) {
        for (ExamineStatus examineStatus : values()) {
            if (examineStatus.getStatus() == status) {
                return examineStatus;
            }
        }
        return null;
    }
}
