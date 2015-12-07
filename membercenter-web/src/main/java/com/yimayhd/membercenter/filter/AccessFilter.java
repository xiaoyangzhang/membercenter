package com.yimayhd.membercenter.filter;

import java.io.IOException;
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

import com.yimayhd.membercenter.utils.MixPolicyEnum;
import com.yimayhd.membercenter.utils.MixPolicyFactory;

public class AccessFilter implements Filter{
	private static final String SIGN = "sign";
	private static final String JOIN_STR = ":";
	private static final String SECURITY_FIELDS = "securityFields";
	private static final String SALT = "salt";
	private static final String INCLUDE_PARAM = "includePaths";
	
	
	private final Set<String > includeSet = new LinkedHashSet<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String pathParam =  filterConfig.getInitParameter(INCLUDE_PARAM);
		if(StringUtils.isNotEmpty(pathParam)){
			String [] paths = pathParam.split(",");
			for(String path: paths ){
				includeSet.add(path);
			}
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestPath = httpRequest.getServletPath();
		if(includeSet.size() == 0 || !isIncluded(requestPath)){
			chain.doFilter(request, response);
			return;
		}
	
		String sign = request.getParameter(SIGN);
		String salt = request.getParameter(SALT);
		String securityFields = request.getParameter(SECURITY_FIELDS);
		
		if(StringUtils.isEmpty(securityFields) || StringUtils.isEmpty(salt) || StringUtils.isEmpty(sign)){
			response.getWriter().write("参数错误!");
			response.getWriter().flush();
			return ;
		}
		
		StringBuilder realSignBuilder = new StringBuilder(MixPolicyFactory.getPolicy(MixPolicyEnum.SIMPLE).mix(sign));
		String [] fields = securityFields.split(JOIN_STR);
		for(String field : fields){
			if(request.getParameter(field) != null){
				realSignBuilder.append(":").append(request.getParameter(field));
			}	
		}

		String realSign = realSignBuilder.toString();
		if(!realSign.equals(sign)){//非法请求
			//FIXME 跳转到错误页面或者直接输出错误结果
			response.getWriter().write("非法访问!");
			response.getWriter().flush();
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isIncluded(String requestPath){
		if(includeSet.contains(requestPath)){
			return true;
		}
		
		return false;
	}
	
}
