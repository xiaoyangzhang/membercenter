package com.yimayhd.membercenter.session;

import com.yimayhd.membercenter.cache.CacheManager;

public class TairSessionStorage implements StorageStrategy {
	private static final String SESSION_KEY_PREFIX = "session_key_";
	private CacheManager cacheManager;
	//unit:seconds
	private int liveTime;
	
	public TairSessionStorage(CacheManager cacheManager,int liveTime) {
		this.cacheManager = cacheManager;
		this.liveTime = liveTime;
	}
	
	@Override
	public void save(String sessionId, SessionObject sessionObject) {
		cacheManager.addToTair(SESSION_KEY_PREFIX + sessionId, sessionObject,liveTime);
	}

	@Override
	public boolean delete(String sessionId) {
		return cacheManager.deleteFromTair(SESSION_KEY_PREFIX + sessionId);
	}

	@Override
	public SessionObject get(String sessionId) {
		SessionObject sessionObject = (SessionObject) cacheManager.getFormTair(SESSION_KEY_PREFIX + sessionId);
		//重新设置有效期
		cacheManager.addToTair(SESSION_KEY_PREFIX + sessionId, sessionObject,liveTime);
		return sessionObject;
	}
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	
}
