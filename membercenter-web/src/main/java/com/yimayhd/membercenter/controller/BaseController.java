
package com.yimayhd.membercenter.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

import com.yimayhd.membercenter.session.Session;
import com.yimayhd.membercenter.session.SessionManager;
import com.yimayhd.membercenter.utils.HttpHolder;

@RestController
public class BaseController {
	@Resource
	public SessionManager tairSessionManager;
    
    public Session getSession(){
		HttpServletRequest request = HttpHolder.getRequest();
		HttpServletResponse response = HttpHolder.getResponse();
		
		return tairSessionManager.getSessioin(request, response);
	}

	public SessionManager getTairSessionManager() {
		return tairSessionManager;
	}

	public void setTairSessionManager(SessionManager tairSessionManager) {
		this.tairSessionManager = tairSessionManager;
	}
    
    
}

