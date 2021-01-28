package com.cy.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *	拦截器设置所有请求允许跨域
 */
@Component
public class CorsInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object arg2) throws Exception {
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		if ("OPTIONS".equals(httpServletRequest.getMethod())){
			httpServletResponse.setStatus(HttpStatus.SC_NO_CONTENT);
		}
		return true;
	}

}
