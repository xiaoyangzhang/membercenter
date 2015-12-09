package com.yimayhd.membercenter.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Description    字符串相关处理工具类
 * @author         zhang jian
 * @since          2015年11月30日
 * @version        V1.0
 */
public class StringTool {
	/**
	 * 
	 * @Title          hideStr 
	 * @Description    把字符串中的部分字符串转换为*
	 * @param target
	 * @param start
	 * @param end
	 * @return
	 */
	public static String hideStr(String target,int start,int end){
		StringBuilder sb = new StringBuilder(target);
		StringBuilder mask = new StringBuilder();
		for(int i = start;i < end; ++i){
			mask.append("*");
		}
		sb.replace(start, end,mask.toString());
		
		return sb.toString();
	}
	
	public static String subString(String target,int start,int end){
		if(StringUtils.isEmpty(target)){
			return target;
		}
		
		return target.substring(start, end);
	}
	
	public static boolean isEmpty(String target){
		return StringUtils.isEmpty(target);
	}
	
	public static boolean isNotEmpty(String target){
		return !StringUtils.isEmpty(target);
	}
	
	public static void main(String [] args){
		System.out.println(hideStr(subString("+8618611865094",3,13),3,7));
	}
}
