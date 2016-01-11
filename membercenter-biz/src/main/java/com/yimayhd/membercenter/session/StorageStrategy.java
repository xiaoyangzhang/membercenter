package com.yimayhd.membercenter.session;

public interface StorageStrategy {
	/**
	 * 保存
	 * @param sessionId
	 * @param sessionObject
	 */
	public void save(String sessionId,SessionObject sessionObject);
	
	/**
	 * 删除
	 * @param sessionId
	 * @return
	 */
	public boolean delete(String sessionId);
	
	/**
	 * 查找
	 * @param sessionId
	 * @return
	 */
	public SessionObject get(String sessionId);
}
