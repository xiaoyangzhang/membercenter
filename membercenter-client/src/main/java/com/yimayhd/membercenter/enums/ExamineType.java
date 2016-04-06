/*
 * FileName: ExaminType.java
 * Author:   liubb
 * Date:     2016年3月26日 下午3:04:12
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
public enum ExamineType {
    
    TALENT("达人", 1),
    MERCHANT("店铺", 2)
    ;
    
    private String name;
    private int id;

    ExamineType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ExamineType getByType(int type){
    	for(ExamineType examineType : ExamineType.values() ){
    		if( examineType.getId() == type ){
    			return examineType ;
    		}
    	}
    	return null ;
    }
    public static boolean has(int id) {
        for (ExamineType eXType : values()) {
            if (eXType.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
