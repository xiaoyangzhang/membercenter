package com.yimayhd.membercenter.util;

import java.util.Random;

public class CodeUtil {
	public static final int[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
	private static final Random random = new Random() ;
	
	public static String getNumberCode(int length){
		StringBuffer sb = new StringBuffer() ;
		int size = NUMBERS.length ;
		for(int i=0 ; i< length; i++ ){
			int index = random.nextInt(size);
			sb.append(NUMBERS[index]) ;
		}
		return sb.toString() ;
	}
}
