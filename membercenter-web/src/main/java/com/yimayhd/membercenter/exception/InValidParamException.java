package com.yimayhd.membercenter.exception;

/**
 * 
 * @author 非法参数运行时异常
 *
 */
public class InValidParamException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1777698932369421723L;
	
	public InValidParamException(){
		super();
	}

	public InValidParamException(String message){
		super(message);
	}
	
	public InValidParamException(String message,Exception e){
		super(message,e);
	}
}
