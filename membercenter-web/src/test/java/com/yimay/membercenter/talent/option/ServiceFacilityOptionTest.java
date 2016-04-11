/*
 * FileName: ServiceFacilityOptionTest.java
 * Author:   liubb
 * Date:     2016年4月11日 下午4:59:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent.option;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.user.client.enums.ServiceFacilityOption;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ServiceFacilityOptionTest {
    
    WIFI("1", 1L << 1, "WIFI"), PARKING("2", 1L << 2, "停车"), CARD_SWIP("3", 1L << 3, "刷卡"),PRIVATE_BOX("4",1L << 4,"包厢"),OPEN_AIR("5",1L << 5,"露天位");

    private String code;
    private long option;
    private String desc;

    private ServiceFacilityOptionTest(String code, long option, String desc) {
        this.code = code;
        this.option = option;
        this.desc = desc;
    }
    
    /**
     * option枚举相加
     * @param firstOption
     * @param secondOption
     * @return
     */
    public static long addOption(ServiceFacilityOptionTest ... optionsArray ){
        long result = 0l;
        for(ServiceFacilityOptionTest serviceFacilityOption : optionsArray){
            result = (result | serviceFacilityOption.getOption());
        }
        return result;
    }
    
    public static long addOption(List<ServiceFacilityOptionTest>  optionList ){
        long result = 0l;
        for(ServiceFacilityOptionTest serviceFacilityOption : optionList){
            result = (result | serviceFacilityOption.getOption());
        }
        return result;
    }
    
    public long getOption() {
        return option;
    }

    public void setOption(long option) {
        this.option = option;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ServiceFacilityOptionTest valueOfCode(String code){
        for(ServiceFacilityOptionTest serviceTypeOption : ServiceFacilityOptionTest.values()){
            if(serviceTypeOption.getCode().equals(code)){
                return serviceTypeOption;
            }
        }
        
        return null;
    }
    
    /**
     * 判断当前option是否包含在传入的option中
     * @param options
     * @return
     */
    public  boolean has(long options) {
        return (option & options) == option;
    }
    
    /**
     * 判断当前options包含哪些枚举
     * @param options
     * @return
     */
    public static List<ServiceFacilityOption> getContainedOptions(long options){
        List<ServiceFacilityOption> optionList = new ArrayList<ServiceFacilityOption>();
        
        for(ServiceFacilityOption serviceTypeOption : ServiceFacilityOption.values()){
            long option = serviceTypeOption.getOption();
            if((option & options) == option){
                optionList.add(serviceTypeOption);
            }
        }
        
        return optionList;
    }

    
    public static void main(String[] args) {
        System.out.println(ServiceFacilityOptionTest.addOption(ServiceFacilityOptionTest.values()));
    }
}
