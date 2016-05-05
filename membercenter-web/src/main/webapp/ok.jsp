<%@page import="java.io.PrintWriter"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.yimayhd.membercenter.client.service.examine.ExamineDealService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	ExamineDealService examineDealService = (ExamineDealService) wac.getBean("examineDealService");
	int code = 0;
	if (examineDealService == null) {
		code = -1;
	}
	PrintWriter write = response.getWriter() ;
	write.println(code);
	write.flush();
	write.close();


%>

