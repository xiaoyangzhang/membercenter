package com.yimayhd.membercenter.filter;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;


public class DefaultTokenManager implements TokenManager {
	private static final String TOKEN_KEY = "token";//DefaultTokenManager.class.getName();
	private static final TokenGenerator TOKEN_GENERATOR = new UUIDTokenGenerator();
	

	@Override
	public String getTokenFromRequest(HttpServletRequest request) {
		if(request.getParameter(TOKEN_KEY) != null){
			return request.getParameter(TOKEN_KEY);
		}
		
		return null;
	}

	@Override
	public String getTokenFromSession(HttpServletRequest request) {
		
		String token = (String) request.getSession().getAttribute(TOKEN_KEY);
		if(token == null){
			token = TOKEN_GENERATOR.generateToken();
			request.getSession().setAttribute(TOKEN_KEY, token);
		}
		
		return token;
	}

	@Override
	public void expireToken(HttpServletRequest request) {
		request.getSession().removeAttribute(TOKEN_KEY);
	}
	
}

class UUIDTokenGenerator implements TokenGenerator{
	
	@Override
	public String generateToken() {
		String token = UUID.randomUUID().toString();
		return token;
	}
	
}


