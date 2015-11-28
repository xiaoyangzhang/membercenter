package com.yimayhd.membercenter;

import net.pocrd.entity.AbstractReturnCode;

/**
 * code区间：[16000000, 17000000)
 * @author wzf
 *
 */
public class MemberReturnCode  extends AbstractReturnCode {
	private int code;
	private String desc ;

	
	public MemberReturnCode(int code, String desc) {
		super(desc, code);
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	

	public static final int SYSTEM_ERROR_C = 16000000;
	public static final MemberReturnCode SYSTEM_ERROR = new MemberReturnCode(SYSTEM_ERROR_C, "系统错误") ;
	
	public static final int DB_WRITE_FAILED_C = 16000001;
	public static final MemberReturnCode DB_WRITE_FAILED = new MemberReturnCode(DB_WRITE_FAILED_C, "写数据库失败") ;
	
	public static final int DB_READ_FAILED_C = 16000002;
	public static final MemberReturnCode DB_READ_FAILED = new MemberReturnCode(DB_READ_FAILED_C, "读数据库失败") ;
	
	public static final int PARAMTER_ERROR_C = 16000003;
	public static final MemberReturnCode PARAMTER_ERROR = new MemberReturnCode(PARAMTER_ERROR_C, "参数 错误") ;
	
	
	
	
	/**************************************ORDER********************************************/
	public static final int BIZ_ORDER_NOT_FOUND_C = 16001000;
	public static final MemberReturnCode BIZ_ORDER_NOT_FOUND = new MemberReturnCode(BIZ_ORDER_NOT_FOUND_C, "订单未找到") ;
	
	
	
	/**************************************user********************************************/
	public static final int USER_NOT_FOUND_C = 16002001;
	public static final MemberReturnCode USER_NOT_FOUND = new MemberReturnCode(USER_NOT_FOUND_C, "用户未找到") ;
	
	
	
	
	
}
