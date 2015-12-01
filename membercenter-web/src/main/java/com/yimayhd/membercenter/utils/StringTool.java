package com.yimayhd.membercenter.utils;

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
	
	
	public static void main(String [] args){
		System.out.println(hideStr("18611865094",3,7));
	}
}
