package com.my.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
	private static final Logger logger =
			LoggerFactory.getLogger(Interceptor.class);
	
	static final String[] EXCLUDE_URL_LIST = {
			"/login", "/join", "/home"
	};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String reqUrl = request.getRequestURL().toString();
		
		/** 로그인체크 제외 리스트 */
		for(String target : EXCLUDE_URL_LIST) {
			if(reqUrl.indexOf(target) > -1) {
				return true;
		}
	}
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		if(userId == null || userId.trim().equals("")) {
			logger.info(">> interceptor catch!!! userId is null.. ");
			session.invalidate();
			
			response.sendRedirect(request.getContextPath() + "/login.do");
			return false;
		}
		
		return true;
	}
}