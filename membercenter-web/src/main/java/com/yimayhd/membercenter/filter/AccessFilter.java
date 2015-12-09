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

import com.yimayhd.membercenter.utils.Encrypt;
import com.yimayhd.membercenter.utils.MixPolicyEnum;
import com.yimayhd.membercenter.utils.MixPolicyFactory;

/**
 * 
 * @Description    请求合法性过滤
 * @author         zhang jian
 * @since          2015年12月7日
 * @version        V1.0
 */
public class AccessFilter implements Filter {
	private static final String SIGN = "sign";
	private static final String JOIN_STR = ":";
	private static final String SECURITY_FIELDS = "securityFields";
	private static final String SALT = "salt";
	private static final String INCLUDE_PARAM = "includePaths";
	private static final String NOTAUTH = "notAuthUrl";
	private String notAuthUrl = "";
	private static final Encrypt ENCRYPT = new Encrypt();
	private static final String DEFAULT_KEY1 = "jfslajfljsdljfldajfd";
	private static final String DEFAULT_KEY2 = "4564s65a4f6we7fsdf8e";
	private static final String DEFAULT_KEY3 = "djsafnsdkfjjsljflsj21";

	private final Set<String> includeSet = new LinkedHashSet<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String pathParam = filterConfig.getInitParameter(INCLUDE_PARAM);
		if (StringUtils.isNotEmpty(pathParam)) {
			String[] paths = pathParam.split(",");
			for (String path : paths) {
				includeSet.add(path);
			}
		}

		notAuthUrl = filterConfig.getInitParameter(NOTAUTH);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestPath = httpRequest.getServletPath();
		if (includeSet.size() == 0 || !isIncluded(requestPath)) {
			chain.doFilter(request, response);
			return;
		}

		String sign = request.getParameter(SIGN);
		String salt = request.getParameter(SALT);
		String securityFields = request.getParameter(SECURITY_FIELDS);

		if (StringUtils.isEmpty(securityFields) || StringUtils.isEmpty(salt) || StringUtils.isEmpty(sign)) {
			request.getRequestDispatcher(notAuthUrl).forward(httpRequest, response);
			return;
		}

		StringBuilder realSignBuilder = new StringBuilder(MixPolicyFactory.getPolicy(MixPolicyEnum.SIMPLE).mix(salt));
		String[] fields = securityFields.split(JOIN_STR);
		for (String field : fields) {
			if (request.getParameter(field) != null) {
				realSignBuilder.append(":").append(request.getParameter(field));
			}
		}
		
		String realSign = ENCRYPT.strEnc(realSignBuilder.toString(), DEFAULT_KEY1, DEFAULT_KEY2, DEFAULT_KEY3);
		if (!realSign.equals(sign)) {// 非法请求
			request.getRequestDispatcher(notAuthUrl).forward(httpRequest, response);

			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	/**
	 * 
	 * @Title isIncluded
	 * @Description 判断是否包含请求的路径
	 * @param requestPath
	 * @return
	 */
	private boolean isIncluded(String requestPath) {
		if (includeSet.contains(requestPath)) {
			return true;
		}

		return false;
	}

}
