package com.yimayhd.membercenter.filter;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Description    token管理
 * @author         zhang jian
 * @since          2015年12月15日
 * @version        V1.0
 */
public interface TokenManager{
	/**
	 * 
	 * @Title          getToken 
	 * @Description    从request中获取一个token
	 * @return
	 */
	public String getTokenFromRequest(HttpServletRequest request);
	
	/**
	 * 
	 * @Title          getTokenFromSession 
	 * @Description    从session中获取一个token，如果无token则生成一个新的token放到session中
	 * @param request
	 * @return
	 */
	public String getTokenFromSession(HttpServletRequest request);
	
	/**
	 * 
	 * @Title          expireToken 
	 * @Description    失效session中的token
	 */
	public void expireToken(HttpServletRequest request);
}
