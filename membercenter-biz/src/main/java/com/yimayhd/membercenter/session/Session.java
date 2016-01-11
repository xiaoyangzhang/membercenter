package com.yimayhd.membercenter.session;

public interface Session {
	/**
	 * 获取sessionId
	 * @return
	 */
	public String getSessionId();
	
	/**
	 * 增加session属性
	 * @param key
	 * @param value
	 */
	public void  addAttribute(String key,Object value);
	
	/**
	 * 根据key查询出session属性
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key);
	
	/**
	 * 删除属性
	 * @param key
	 */
	public void removeAbbtribute(String key);
	
	/**
	 * session是否过期
	 * @return
	 */
	public boolean isExpired();
	
}
