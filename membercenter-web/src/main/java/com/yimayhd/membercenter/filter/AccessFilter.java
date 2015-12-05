package com.yimayhd.membercenter.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yimayhd.membercenter.utils.MD5Util;

public class AccessFilter implements Filter{
	private static final String SIGN = "sign";
	private static final String SALT = "salt";
	private static final String JOIN_STR = "@";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String sign = request.getParameter(SIGN);
		HttpSession session = httpRequest.getSession();
		String sessionId = session.getId();
		String salt = (String) session.getAttribute(SALT);
		// FIXME
		if (salt == null) {
			
		}

		String realSign = MD5Util.getMD5Code(sessionId + JOIN_STR + salt);
		if(!realSign.equals(sign)){//非法请求
			//FIXME 跳转到错误页面或者直接输出错误结果
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
