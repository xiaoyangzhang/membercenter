package com.yimayhd.membercenter.utils;

import com.yimayhd.membercenter.exception.InValidParamException;

/**
 * 
 * @Description    断言工具类
 * @author         zhang jian
 * @since          2015年11月19日
 * @version        V1.0
 */
public class Asserts {
	
	/**
	 * 
	 * @Title          AssertNotNull 
	 * @Description    断言非空
	 * @param target
	 * @param paramName
	 */
	public static <T>  void AssertNotNull(T target,String paramName){
		if(target == null){
			throw new InValidParamException(paramName + " should not be null!");
		}
	}
	
	/**
	 * 
	 * @Title          AssertStringNotEmpty 
	 * @Description    断言字符串非空
	 * @param target
	 * @param paramName
	 */
	public static void AssertStringNotEmpty(String target,String paramName){
		AssertNotNull(target,paramName);
		if("".equals(target)){
			throw new InValidParamException(paramName + " should not be empty!");
		}
	}
}
