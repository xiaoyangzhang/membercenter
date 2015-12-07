package com.yimayhd.membercenter.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description    混淆策略的工厂
 * @author         zhang jian
 * @since          2015年12月7日
 * @version        V1.0
 */
public class MixPolicyFactory {
	private static  Map<MixPolicyEnum,MixPolicy> holder = new HashMap<MixPolicyEnum,MixPolicy>();
	static {
		holder.put(MixPolicyEnum.SIMPLE, new SimpleMixPolicy());
	}
	
	public static MixPolicy getPolicy(MixPolicyEnum policy){
		return holder.get(policy);
	}
}
