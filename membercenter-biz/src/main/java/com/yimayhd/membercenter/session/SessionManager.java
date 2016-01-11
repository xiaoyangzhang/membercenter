package com.yimayhd.membercenter.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManager {
	public Session getSessioin(HttpServletRequest request,HttpServletResponse response);
	
	//public Session clearSessioin(HttpServletRequest request,HttpServletResponse response);
}
