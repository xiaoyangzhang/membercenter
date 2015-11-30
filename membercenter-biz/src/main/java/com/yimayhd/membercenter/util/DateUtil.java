package com.yimayhd.membercenter.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String DAY_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT="HH-mm-ss";
    public static final String TIME_MS_FORAMT = "HH:mm:ss:SSS";
    /**
     * 日期+时间格式，包括毫秒
     */
    public static final String DATE_TIME_MS_FORMAT = DAY_FORMAT + " " + TIME_MS_FORAMT;
    
    /**
     * 日期类型转换
     * <li>将Date类型转换成Long类型</li>
     * @param date
     * @return 返回long类型日期
     */
    public static long dateToLong(Date date) {
        return date == null ? 0 : date.getTime();
    }

    /**
     * time=0 过滤时间为1970-01-01 情况
     * @param time
     * @return
     */
    public static Date longToDate(long time){
        if(time!=0){
            Date date=new Date(time);
            return date;
       }
       return null;
    }
	
	public static String format(Date date, String format){
		if( date == null || format == null || "".equals(format)){
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	public static Date parseDate(String dayFormat, String format){
		if( dayFormat == null || format == null || "".equals(format)){
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(dayFormat);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("day="+dayFormat+" format="+format, e);
		}
		return null;
	}
	
	public static Date getDateStart(Date date){
		if( date == null ){
			return null;
		}
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime() ;
	}
	public static Date getDateEnd(Date date){
		if( date == null ){
			return null;
		}
		Calendar calendar = Calendar.getInstance() ;
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime() ;
	}
}
