/*
 * FileName: MerchantOptionTest.java
 * Author:   liubb
 * Date:     2016年3月30日 上午10:22:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum MerchantOptionTest {

    EAT("1", 1L << 1, "必吃店铺"), TALENT("2", 1L << 2, "达人店铺"), MERCHANT("3", 1L << 3, "商家");

    private String code;
    private long option;
    private String desc;

    private MerchantOptionTest(String code, long option, String desc) {
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
    public static long addOption(MerchantOptionTest ... optionsArray ){
        long result = 0l;
        for(MerchantOptionTest merchantOption : optionsArray){
            result = (result | merchantOption.getOption());
        }
        return result;
    }
    
    public static long addOption(List<MerchantOptionTest>  optionList){
        long result = 0l;
        for(MerchantOptionTest merchantOption : optionList){
            result = (result | merchantOption.getOption());
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

    public static MerchantOptionTest valueOfCode(String code){
        for(MerchantOptionTest merchantOption : MerchantOptionTest.values()){
            if(merchantOption.getCode().equals(code)){
                return merchantOption;
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
    public static List<MerchantOptionTest> getContainedMerchantOptions(long options){
        List<MerchantOptionTest> optionList = new ArrayList<MerchantOptionTest>();
        
        for(MerchantOptionTest merchantOption : MerchantOptionTest.values()){
            long option = merchantOption.getOption();
            if((option & options) == option){
                optionList.add(merchantOption);
            }
        }
        
        return optionList;
    }

    
    public static void main(String[] args) {
        System.out.println(MerchantOptionTest.MERCHANT.getOption());
    }
}
