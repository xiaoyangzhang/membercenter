package com.yimayhd.membercenter.annot;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.util.DateUtil;

import net.pocrd.dubboext.DubboExtProperty;

/**
 * 
 * @author wuzhengfei
 *
 */
public class MethodLoggerInterceptor  implements MethodInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(MethodLoggerInterceptor.class);
	
	private static final String separator = "   ";
	
    /**
     * 如果有方法级注释，那么使用方法级注释，否则使用参数级注释，如果两个注释都没有，那么返回
     */
    public Object invoke(MethodInvocation invocation) throws Throwable{
    	boolean isCatchException = false;
    	boolean isPrintArguments = true;
    	boolean isPrintResult = true;
    	boolean isHttpApi = false;
    	
    	Method method = invocation.getMethod();
    	MethodLogger  methodLogger  = method.getAnnotation(MethodLogger.class);
    	if( methodLogger != null ){
    		isCatchException = methodLogger.isCatchException();
        	isPrintArguments = methodLogger.isPrintArguments();
        	isPrintResult = methodLogger.isPrintResult();
        	isHttpApi = methodLogger.isHttpApi();
    	}
    	
    	long start = System.currentTimeMillis() ;
    	String uuid = UUID.randomUUID().toString() ;
    	Object[] args = invocation.getArguments();
    	String methodName = method.getName() ;
    	Class<?>[] parameterTypes = method.getParameterTypes() ;
    	String className = invocation.getThis().getClass().getSimpleName() ;
   
    	
    	String argumentLogInfo ;
    	if( isPrintArguments ){
    		 argumentLogInfo = getArgumentLogInfo(parameterTypes, args);
    	}else{
    		argumentLogInfo = "" ;
    	}
    	String entranceLog = getEntranceLog(uuid, className, methodName, argumentLogInfo);
    	logger.info(entranceLog);
    	
    	Object resultValue = null;
    	if( !isCatchException ){
    		resultValue = invocation.proceed();
    	}else{
    		try {
    			resultValue = invocation.proceed();
			} catch (Exception e) {
				if( isHttpApi ){
					DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
				}
				logger.error(entranceLog, e);
			}
    	}
    	Class<?> returnType = method.getReturnType();
    	String resultLoggerInfo = null;
    	if( isPrintResult ){
    		resultLoggerInfo = getResultLoggerInfo(returnType, resultValue);
    	}else{
    		resultLoggerInfo = "" ;
    	}
    	String exitLog = getExitLogger(uuid, className, methodName, argumentLogInfo, start, resultLoggerInfo);
    	logger.info( exitLog );
    	return resultValue;
	}
    
    private String getEntranceLog(String uuid, String className, String methodName, String argumentLoggerInfo){
    	StringBuffer sb = new StringBuffer("UUID=").append(uuid)
    			.append(separator).append(className)
    			.append(separator).append(methodName)
    			.append(separator).append(argumentLoggerInfo);
    	return sb.toString();
    }
    
    private String getExitLogger(String uuid, String className, String methodName, String argumentLoggerInfo, long start, String resultLoggerInfo){
    	long cost = System.currentTimeMillis() - start;
    	StringBuffer sb = new StringBuffer();
    	sb.append("UUID=").append(uuid).append(separator)
    		.append(className).append(separator)
    		.append(methodName).append(separator)
    		.append("cost=").append(cost).append("ms").append(separator)
    		.append(argumentLoggerInfo).append(separator)
			.append(resultLoggerInfo);
    	return sb.toString();
    }
    
    private String getResultLoggerInfo(Class<?> returnType, Object resultValue){
    	String resultLoggerInfo = value2String(returnType, resultValue);
    	StringBuffer sb = new StringBuffer();
    	sb.append("Result=").append(resultLoggerInfo);
    	return sb.toString();
    }
    
    /**
     * 将入参信息装填到返回StringBuffer中
     * @param parameterTypes
     * @param args
     */
    private String getArgumentLogInfo( Class<?>[] parameterTypes, Object[] args){
    	StringBuffer sb = new StringBuffer("");
    	if(parameterTypes == null || parameterTypes.length == 0 || args == null || args.length == 0  || parameterTypes.length != args.length){
    		return sb.toString();
    	}
    	sb.append("[ ") ;
    	int length = args.length ;
    	
		for( int i=0 ; i<length ; i++ ){
			Class<?> parameterType = parameterTypes[i] ;
			
			String paramtername = parameterType.getSimpleName() ;
			sb.append(paramtername).append("=");
			Object paramterValue = args[i] ;
			if( paramterValue == null ){
				sb.append("null") ;
			}else{
				String result = value2String(parameterType, paramterValue);
				sb.append(result) ;
			}
			sb.append(separator) ;
		}
    	sb.append(" ]");
    	return sb.toString();
    }
    
    /**
     * 将对象输出出来
     * @param type
     * @param value
     * @return
     */
    private String value2String(Class<?> type, Object value){
    	if( type == null || value == null ){
    		return "" ;
    	}
    	try{
	    	boolean isPrimitive = type.isPrimitive() ;
			if( isPrimitive ){
				return String.valueOf(value) ;
			}else if( value instanceof Date ){
				return DateUtil.format((Date) value, DateUtil.DATE_TIME_MS_FORMAT) ;
			}else if( value instanceof String ){
				return value.toString();
			}else{
				return JSON.toJSONString(value) ;
			}
    	}catch( Exception e){
    		logger.error("value2String", e);
    	}
    	return "";
    }
    
}
