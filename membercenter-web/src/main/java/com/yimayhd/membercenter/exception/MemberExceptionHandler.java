package com.yimayhd.membercenter.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @Description    controller异常处理类
 * @author         zhang jian
 * @since          2015年12月1日
 * @version        V1.0
 */
public class MemberExceptionHandler implements HandlerExceptionResolver {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		LOGGER.error("发生异常:",ex);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		
		// 根据不同错误转向不同页面
		if (ex instanceof InValidParamException) {
			mv.addObject("message",ex.getMessage());
		} else {
			mv.addObject("message",ex.getMessage());
		}
		
		return mv;
	}

}
