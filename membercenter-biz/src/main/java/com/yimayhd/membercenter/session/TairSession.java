package com.yimayhd.membercenter.session;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.yimayhd.membercenter.cache.CacheManager;

public class TairSession implements Session{
	private static final String COOKIE_SESSION_NAME = "session_token";
	//private ThreadLocal<String> sessionIds = new ThreadLocal<String>();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private StorageStrategy storageStrategy ;
	private CacheManager cacheManager;
	//unit:seconds
	private int liveTime;
	
	public TairSession(HttpServletRequest request,HttpServletResponse response,CacheManager cacheManager,int liveTime){
		this.request = request;
		this.response = response;
		this.liveTime = liveTime;
		this.cacheManager = cacheManager;
		storageStrategy = new TairSessionStorage(this.cacheManager,this.liveTime);
	}
	
	@Override
	public String getSessionId() {
		String sessionId = getSessionId(request);
		//判断sessionId是否有效
		if(StringUtils.isEmpty(sessionId)){
			sessionId = response.getHeader(COOKIE_SESSION_NAME);
		}
		
		if(StringUtils.isEmpty(sessionId)){

			sessionId = generateSessionId();
			SessionObject sessionObject = new SessionObject();
			storageStrategy.save(sessionId, sessionObject);
			response.setHeader(COOKIE_SESSION_NAME, sessionId);
			//刷新到客户端
			flush(response,sessionId);
		}else{
			SessionObject cacheObject = storageStrategy.get(sessionId);
			if(cacheObject == null){ //失效，或者不存在
				SessionObject sessionObject = new SessionObject();
				sessionId = generateSessionId();
				storageStrategy.save(sessionId, sessionObject);		
			}
		}
		
		
		return sessionId;
	}

	@Override
	public void addAttribute(String key, Object value) {
		if(isExpired() == false){
			String sessionId = getSessionId();
			SessionObject sessionObject = storageStrategy.get(sessionId);
			sessionObject.put(key, value);
			storageStrategy.save(sessionId, sessionObject);
		}
	}

	@Override
	public Object getAttribute(String key) {
		if(isExpired() == false){
			String sessionId = getSessionId();
			SessionObject cacheSessionObject = storageStrategy.get(sessionId);
			
			return cacheSessionObject.get(key);
		}
		
		return null;
	}

	@Override
	public void removeAbbtribute(String key) {
		if(isExpired() == false){
			String sessionId = getSessionId();
			SessionObject sessionObject = storageStrategy.get(sessionId);
			sessionObject.remove(key);
			storageStrategy.save(sessionId, sessionObject);
		}
	}
	
	public String generateSessionId() {
		return UUID.randomUUID().toString();
	}

	public void flush(HttpServletResponse response,String sessionId) {
		// 设置cookie信息
		Cookie cookie = new Cookie(COOKIE_SESSION_NAME, sessionId);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public String getSessionId(HttpServletRequest request){
		String sessionId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (COOKIE_SESSION_NAME.equals(name)) {
				sessionId = cookie.getValue();
				break;
			}
		}
		
		return sessionId;
	}

	@Override
	public boolean isExpired() {
		/**
		String sessionId = getSessionId(request);
		if (StringUtils.isEmpty(sessionId)) {
			return true;
		}

		SessionObject cacheObject = storageStrategy.get(sessionId);
		if (cacheObject == null) { // 失效，或者不存在
			return true;
		}
	*/
		return false;
	}

}
