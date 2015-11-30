package com.yimayhd.membercenter.utils;

public class TimeElapseCaculate {
	private static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	public static final Long UNABLE_TO_CACULATE = -1L;
	
	public static Long startSnapshort(){
		Long currentMill = System.currentTimeMillis();
		threadLocal.set(currentMill);
		
		return currentMill;
	}
	
	public static Long endSnapshort(){
		Long currentMill = System.currentTimeMillis();
		if(threadLocal.get() == null){
			return UNABLE_TO_CACULATE;
		}
		Long start = threadLocal.get();
		threadLocal.remove();
		
		return (currentMill - start );
	}
}
