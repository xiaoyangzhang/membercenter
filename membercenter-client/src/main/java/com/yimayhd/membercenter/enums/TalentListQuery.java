/*
 * FileName: TalentListQuery.java
 * Author:   liubb
 * Date:     2016年3月19日 上午11:59:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum TalentListQuery {

    TANLET_LIST(1, "达人列表页"),
    
    TALENT_SERACH(2, "达人搜索页");
    
    TalentListQuery(int type, String desc){
        this.desc = desc;
        this.type = type;
    }
    
    private int type;
    
    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
