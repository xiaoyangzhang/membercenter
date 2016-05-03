package com.yimay.membercenter.talent.option;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.user.client.enums.ServiceTypeOption;

public enum OptionTest {
	TRAVEL("1", 1L << 1, "全程伴游"), CAR("2", 1L << 2, "包车服务"), ASK("3", 1L << 3, "咨询规划"),PRIVATE("4",1L << 4,"私人定制");

	private String code;
	private long option;
	private String desc;

	private OptionTest(String code, long option, String desc) {
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
	public static long addOption(OptionTest ... optionsArray ){
		long result = 0l;
		for(OptionTest serviceTypeOption : optionsArray){
			result = (result | serviceTypeOption.getOption());
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

	public static OptionTest valueOfCode(String code){
		for(OptionTest serviceTypeOption : OptionTest.values()){
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
	public static List<OptionTest> getContainedOptions(long options){
		List<OptionTest> optionList = new ArrayList<OptionTest>();
		
		for(OptionTest serviceTypeOption : OptionTest.values()){
			long option = serviceTypeOption.getOption();
			if((option & options) == option){
				optionList.add(serviceTypeOption);
			}
		}
		
		return optionList;
	}

	
	public static void main(String[] args) {
//	    System.out.println(ServiceTypeOption.addOption(ServiceTypeOption.values()));
	    System.out.println(OptionTest.TRAVEL.has(2));
	}
}
