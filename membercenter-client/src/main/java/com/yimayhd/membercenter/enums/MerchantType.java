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
public enum MerchantType {
    
    TALENT("旅游线路达人", 1),
    MERCHANT("店铺", 2),
    TOUR_COR("旅游企业", 3),
    HOTEL("酒店", 4),
    SCENIC("景区", 5),
    CITY_COR("同城活动企业", 6),
    //以下为旅游企业类型的两个身份
    TRAVEL_AGENCY("旅行社", 7),
    TRAVLE_SERVICE("旅游商务服务公司", 8)
    ;
    
    private String name;
    private int type;

    MerchantType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public static MerchantType getByType(int type){
    	for(MerchantType examineType : MerchantType.values() ){
    		if( examineType.getType() == type ){
    			return examineType ;
    		}
    	}
    	return null ;
    }
    public static boolean has(int id) {
        for (MerchantType eXType : values()) {
            if (eXType.getType() == id) {
                return true;
            }
        }
        return false;
    }
}
