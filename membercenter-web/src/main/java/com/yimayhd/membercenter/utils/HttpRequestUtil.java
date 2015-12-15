package com.yimayhd.membercenter.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
	
	/**
	 * 
	 * @Title          isAjax 
	 * @Description    判断是否是ajax调用
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		if(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)){
			return true;
		}
		
		return false;
	}
}
