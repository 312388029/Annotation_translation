package com.cy.common.interceptor;

import com.cy.api.vm.ResponseJsonVM;
import com.cy.common.annotation.IgnoreTokenAuth;
import com.cy.common.constants.Constants;
import com.cy.common.util.ReqResUtils;
import com.cy.common.util.SecretUtil;
import com.cy.service.impl.RedisHashService;
import com.cy.service.impl.RedisStringService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Value("${cy.package}")
	private String packageName;

	private Logger             logger = LoggerFactory.getLogger(TokenInterceptor.class);
	@Autowired
	private RedisStringService redisStringService;
	@Autowired
	private RedisHashService   redisHashService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断是否为有忽略校验的注解
		if (handler instanceof HandlerMethod) {
			IgnoreTokenAuth ignoreAuth = ((HandlerMethod) handler).getMethodAnnotation(IgnoreTokenAuth.class);
			if (ignoreAuth != null) {
				return true;
			}
		} else {
			return true;
		}

		String token = ReqResUtils.getToken(request);
		if (StringUtils.isBlank(token)) {
			ReqResUtils.printJSON(response, ResponseJsonVM.fail(Constants.TOKEN_OVERTIME, "Token不能为空"));
			return false;
		}
		String uri = SecretUtil.slashRemoveRepeat(request.getRequestURI());
		Integer startLength = 0;
		if (packageName != null){
			startLength = packageName.length();
		}
		String uriPrefix = uri.substring(startLength, uri.indexOf("/", startLength+1) + 1);

		boolean flag = false;
		if ("/manage/".equals(uriPrefix)) {
			flag = updateToken(token, Constants.MANAGE_TOKEN_PREFIX);
		} else if ("/mobile/".equals(uriPrefix)) {
			flag = updateToken(token, Constants.MOBILE_TOKEN_PREFIX);
		}

		if (!flag) {
			ReqResUtils.printJSON(response, ResponseJsonVM.fail(Constants.TOKEN_OVERTIME, "Token超时"));
			return false;
		}
		return true;
	}

	/**
	 * 根据前缀更新Token
	 *
	 * @param token
	 * @param prefix
	 * @return
	 */
	private boolean updateToken(String token, final String prefix) {
		String tokenPrefix = token.substring(0, token.indexOf("_") + 1);
		if (!prefix.equals(tokenPrefix)) {
			return false;
		}
		String value = redisStringService.get(token);
		if (StringUtils.isBlank(value)) {
			return false;
		}

		// 刷新Token过期时间
		redisStringService.excuteExpireToken(token, prefix + value);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
