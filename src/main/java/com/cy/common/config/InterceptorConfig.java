package com.cy.common.config;

import com.cy.common.interceptor.CorsInterceptor;
import com.cy.common.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private TokenInterceptor       tokenInterceptor;
	@Autowired
	private CorsInterceptor        corsInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
		//registry.addInterceptor(ipBlacklistInterceptor).addPathPatterns("/**");
		// 拦截对应请求校验Token
		String[] pathPatterns = {"/manage/**", "/mobile/**"};
		registry.addInterceptor(tokenInterceptor).addPathPatterns(pathPatterns);
	}

}
