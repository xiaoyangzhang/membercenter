package com.yimayhd.membercenter.annot;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注释，只能标注方法
 * @author wuzhengfei
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLogger {
	
	/**
	 * 是否try catch异常信息
	 */
	boolean isCatchException() default false;
	
	boolean isHttpApi() default false;
	/**
	 * 打印入参
	 */
	boolean isPrintArguments() default true;
	
	/**
	 * 打印结果
	 */
	boolean isPrintResult() default true;
	
	

}
