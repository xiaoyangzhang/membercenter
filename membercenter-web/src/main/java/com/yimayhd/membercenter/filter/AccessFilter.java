package com.yimayhd.membercenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AccessFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取请求参数
		//根据请求参数按照一定的规则进行排序
		//根据salt，按照md5进行加密出sign
		//比较sign值是否一致
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
