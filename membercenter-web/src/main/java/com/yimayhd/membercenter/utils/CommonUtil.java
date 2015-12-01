package com.yimayhd.membercenter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @Description 通用工具类
 * @author zhang jian
 * @since 2015年11月18日
 * @version V1.0
 */
public class CommonUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 
	 * @Title generateRandomCode
	 * @Description 根据指定长度生成1-10的随机码
	 * @param length
	 *            需要生成的随机码长度
	 * @return
	 */
	public static String generateRandomCode(int length) {
		String str = "";
		for (int i = 0; i < length; ++i) {
			str += (int) (Math.random() * 10);
		}
		return str;
	}

	public static <T> String toJson(T obj) {
		try {
			if (obj != null) {
				return MAPPER.writeValueAsString(obj);
			}
		} catch (JsonProcessingException e) {
			//ignore
		}

		return "";
	}

}
