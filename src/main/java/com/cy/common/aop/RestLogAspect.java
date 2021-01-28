package com.cy.common.aop;


import com.cy.common.util.ReqResUtils;
import com.cy.service.impl.RedisStringService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest接口日志打印切面 打印传入请求参数 打印传出响应数据——这里的响应指正常return返回，不包含各种异常响应
 */
@Aspect
@Component
public class RestLogAspect {
	private final Logger                 logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private       OperationConfigService operationConfigService;
//	@Autowired
//	private       OperationLogService    operationLogService;
//	@Autowired
//	private       LoginLogService        loginLogService;
	@Autowired
	public RedisStringService redisStringService;

	@Pointcut("within(com.cy.rest..*)")
	public void restLog() {
	}

	@Before("restLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//记录ip
		logger.info("ip            : " + request.getRemoteAddr());
		// 记录下请求内容
		logger.info("Url           : " + request.getRequestURL().toString());
		logger.info("Http_Method   : " + request.getMethod());
		// logger.info("IP : " + request.getRemoteAddr());
		logger.info("Class_Monthod : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		// logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("Params        : ");

		ReqResUtils.printParams(request);
	}

//	@AfterReturning(returning = "ret", pointcut = "restLog()")
//	public void doAfterReturning(Object ret) {
//		// 处理完请求，返回内容
//		String responseStr = JSONObject.toJSONString(ret);
//		if(responseStr.length() > 50) {
//			responseStr = "Query data too long";
//		}
//		logger.info("Rresponse     : " + responseStr);
//
//		/** 处理完请求无报错正常返回成功码时，添加操作日志 **/
//		addOperationLog(ret);
//	}
//
//	private void addOperationLog(Object ret) {
//		JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(ret));
//		String code = json.getString("code");
//		if ("0000".equals(code)) {
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//					.getRequestAttributes();
//			HttpServletRequest request = attributes.getRequest();
//			String uri = request.getRequestURI();
//			OperationConfig config = operationConfigService.getByUri(uri);
//			if (config != null) {
//				String token = null;
//				if (uri.contains("/login")) {// 登录存入登录日志表
//					JSONObject data = json.getJSONObject("data");
//					token = data.getString("token");
//					loginLog(request, token, uri, config.getName(), null);
//				} else if (uri.contains("/logout")) {// 退出存入登录日志表
//					loginLog(request, null, uri, config.getName(), json.getString("data"));
//				} else {// 存入操作日志表
//					operationLog(request, request.getParameter("token"), uri, config.getName());
//				}
//			}
//		}
//	}
//
//	private void loginLog(HttpServletRequest request, String token, String uri, String operation, String userId) {
//		LoginLog loginLog = new LoginLog();
//		loginLog.setSource(uri.substring(1, uri.indexOf("/", 1)));
//		loginLog.setUri(uri);
//		loginLog.setIp(IpUtil.getIpAddress(request));
//		loginLog.setOperation(operation);
//		if (StringUtils.isNotBlank(token)) {
//			loginLog.setUserId(redisStringService.get(token));
//		} else {
//			loginLog.setUserId(userId);
//		}
//		loginLogService.insert(loginLog);
//	}
//
//	private void operationLog(HttpServletRequest request, String token, String uri, String operation) {
//		OperationLog operationLog = new OperationLog();
//		operationLog.setSource(uri.substring(1, uri.indexOf("/", 1)));
//		operationLog.setUri(uri);
//		operationLog.setIp(IpUtil.getIpAddress(request));
//		operationLog.setOperation(operation);
//		if (StringUtils.isNotBlank(token)) {
//			operationLog.setUserId(redisStringService.get(token));
//		}
//		operationLogService.insert(operationLog);
//	}

}
