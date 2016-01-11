package com.yimayhd.membercenter.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.cache.CacheManager;

public class TairSessionManager implements SessionManager{
	@Autowired
	private CacheManager cacheManager;
	
	//session的有效时间
	private int sessionLiveTime;

	@Override
	public Session getSessioin(HttpServletRequest request, HttpServletResponse response) {
		return new TairSession(request,response,cacheManager,sessionLiveTime);
	}

	public int getSessionLiveTime() {
		return sessionLiveTime;
	}

	public void setSessionLiveTime(int sessionLiveTime) {
		this.sessionLiveTime = sessionLiveTime;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
}
