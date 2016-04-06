/*
 * FileName: ExaminePageNo.java
 * Author:   liubb
 * Date:     2016年4月6日 下午3:08:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈PAGENO〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ExaminePageNo {
    PAGE_ONE("第一页", 1),
    PAGE_TWO("第二页", 2)
    ;
    
    private String name;
    private int pageNO;

    ExaminePageNo(String name, int pageNO) {
        this.name = name;
        this.pageNO = pageNO;
    }

    public String getName() {
        return name;
    }

    public int getPageNO() {
        return pageNO;
    }
    
    public static boolean has(int pageNO) {
        for (ExaminePageNo eXType : values()) {
            if (eXType.getPageNO() == pageNO) {
                return true;
            }
        }
        return false;
    }
}
