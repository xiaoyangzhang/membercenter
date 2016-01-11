package com.yimayhd.membercenter.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionObject implements Sessionable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5332429192750586301L;
	
	private Map<String,Object> attributes = new ConcurrentHashMap<String,Object> ();
	
	public Object put(String key,Object value){
		return attributes.put(key, value);
	}
	
	public Object remove(String key){
		return attributes.remove(key);
	}
	
	public Object get(String key){
		return attributes.get(key);
	}
}
