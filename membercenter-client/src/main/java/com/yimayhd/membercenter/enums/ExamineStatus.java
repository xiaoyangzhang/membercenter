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
    EXAMIN_ING("未审核", 2),
    EXAMIN_ERROR("审核失败", 3)
    ;
    
    private String name;
    private int id;

    ExamineStatus(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ExamineStatus getByStatus(int status) {
        for (ExamineStatus examineStatus : values()) {
            if (examineStatus.getId() == status) {
                return examineStatus;
            }
        }
        return null;
    }
}
