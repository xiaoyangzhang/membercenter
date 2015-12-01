package com.yimayhd.membercenter;

import net.pocrd.entity.AbstractReturnCode;

/**
 * code区间：[16000000, 17000000)
 * @author wzf
 *
 */
public class MemberReturnCode  extends AbstractReturnCode {

	
	public MemberReturnCode(int code, String desc) {
		super(desc, code);
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
	
	public static final int PAGE_QUERY_ORDER_FAILED_C = 16001001;
	public static final MemberReturnCode PAGE_QUERY_ORDER_FAILED = new MemberReturnCode(PAGE_QUERY_ORDER_FAILED_C, "分页查询订单失败") ;
	
	
	
	/**************************************user********************************************/
	public static final int USER_NOT_FOUND_C = 16002001;
	public static final MemberReturnCode USER_NOT_FOUND = new MemberReturnCode(USER_NOT_FOUND_C, "用户未找到") ;
	
	public static final int USER_ERROR_C = 16002002;
	public static final MemberReturnCode USER_ERROR = new MemberReturnCode(USER_ERROR_C, "用户错误") ;

	public static final int USER_REGISTERED_C = 16002002;
	public static final MemberReturnCode USER_REGISTERED = new MemberReturnCode(USER_REGISTERED_C, "用户已注册") ;
	

	
	/**************************************特权********************************************/
	public static final int UPTATE_PRIVILEGE_STATUS_ERROR_C = 16003004;
	public static final MemberReturnCode UPTATE_PRIVILEGE_STATUS_ERROR = new MemberReturnCode(UPTATE_PRIVILEGE_STATUS_ERROR_C,"更新特权状态失败");
	



	/**************************************merchant********************************************/
	public static final int MERCHANT_NOT_FOUND_ERROR_C = 16004000;
	public static final MemberReturnCode MERCHANT_NOT_FOUND_ERROR = new MemberReturnCode(MERCHANT_NOT_FOUND_ERROR_C, "商家不存在") ;
	
	
	
	/**************************************member********************************************/
	public static final int MEMBER_NOT_FOUND_C = 16005000;
	public static final MemberReturnCode MEMBER_NOT_FOUND = new MemberReturnCode(MEMBER_NOT_FOUND_C, "会员信息未找到") ;
	
	
	
	/**************************************item********************************************/
	public static final int PAGE_QUERY_ITEM_FAILED_C = 16006000;
	public static final MemberReturnCode PAGE_QUERY_ITEM_FAILED = new MemberReturnCode(PAGE_QUERY_ITEM_FAILED_C, "分页查询商品信息失败") ;
	
	
}
