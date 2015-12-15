package com.yimayhd.membercenter.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.utils.HttpRequestUtil;

public class TokenFilter implements Filter{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private static final String CHECK_PATH_PARAM = "checkPaths";
	private static final String NEED_TOKEN_PATH_PARAM = "tokenPaths";
	private static final String NOTAUTH = "notAuthUrl";
	private final Set<String> checkPathSet = new LinkedHashSet<String>();
	
	private final Set<String> tokenPathSet = new LinkedHashSet<String>();
	
	private final TokenManager tokenManager = new DefaultTokenManager();
	private String notAuthUrl = "";
	private static final String TOKEN_KEY = "token";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String pathParam = filterConfig.getInitParameter(CHECK_PATH_PARAM);
		String tokenPaths = filterConfig.getInitParameter(NEED_TOKEN_PATH_PARAM);
		if (StringUtils.isNotEmpty(pathParam)) {
			String[] paths = pathParam.split(",");
			for (String path : paths) {
				checkPathSet.add(path);
			}
		}
		
		if(StringUtils.isNotEmpty(tokenPaths)){
			String[] paths = tokenPaths.split(",");
			for (String path : paths) {
				tokenPathSet.add(path);
			}
		}
		
		notAuthUrl = filterConfig.getInitParameter(NOTAUTH);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestPath = httpRequest.getServletPath();
		if (!isNeedCheck(requestPath)) {
			if(isNeedToken(requestPath)){ //授予一个token
				String sessionToken = tokenManager.getTokenFromSession(httpRequest);
				httpRequest.setAttribute(TOKEN_KEY,sessionToken);
				//chain.doFilter(request, response);
				
			}
		}else {
			String reqToken = tokenManager.getTokenFromRequest(httpRequest);
			String sessionToken = tokenManager.getTokenFromSession(httpRequest);
			if(!sessionToken.equals(reqToken)){
				if(HttpRequestUtil.isAjax(httpRequest)){//是否是ajax调用
					response.setContentType("application/json");
					PrintWriter writer = response.getWriter();
					String json = MAPPER.writeValueAsString(new Response().failure("无效token!"));
					writer.write(json);
					writer.flush();
				}else{
					request.getRequestDispatcher(notAuthUrl).forward(httpRequest, response);
				}
				
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @Title isIncluded
	 * @Description 判断是否包含请求的路径
	 * @param requestPath
	 * @return
	 */
	private boolean isNeedCheck(String requestPath) {
		if (checkPathSet.contains(requestPath)) {
			return true;
		}

		return false;
	}
	
	private boolean isNeedToken(String requestPath){
		if (tokenPathSet.contains(requestPath)) {
			return true;
		}

		return false;
	}

}
