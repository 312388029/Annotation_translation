package com.cy.common.util;

import com.alibaba.fastjson.JSONObject;
import com.cy.common.exception.NoPermissionException;
import com.cy.common.exception.ParamFormatException;
import com.cy.common.exception.ParamsNullException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ReqResUtils {
	private static Logger log = LoggerFactory.getLogger(ReqResUtils.class);

	private ReqResUtils() {

	}

	/**
	 * 抛出无权限异常
	 *
	 * @throws NoPermissionException
	 */
	public static void throwNoPremission() throws NoPermissionException {
		throw new NoPermissionException();
	}

	public static Integer getIntegerNotNull(HttpServletRequest request, String name)
			throws ParamsNullException, ParamFormatException {
		Integer param = getInteger(request, name);
		if (param == null) {
			throw new ParamsNullException(name);
		}
		return param;
	}

	public static Integer getInteger(HttpServletRequest request, String name) throws ParamFormatException {
		return getIntegerToDefault(request, name, null);
	}

	/**
	 * 获取当前页数
	 *
	 * @param request
	 * @return
	 */
	public static Integer getPageNum(HttpServletRequest request) {
		return getIntegerToDefault(request, "pageNum", "1");
	}

	/**
	 * 获取每页条数
	 *
	 * @param request
	 * @return
	 */
	public static Integer getPageSize(HttpServletRequest request) {
		return getIntegerToDefault(request, "pageSize", "10");
	}

	/**
	 * 从两个地方获取toke
	 *
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (StringUtils.isBlank(token)) {
			token = request.getHeader("token");
		}
		return token;
	}

	/**
	 * 从两个地方获取toke
	 *
	 * @param request
	 * @return
	 */
	public static String getTokenNotNull(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (StringUtils.isBlank(token)) {
			token = request.getHeader("token");
		}
		if (StringUtils.isBlank(token)) {
			throw new ParamsNullException("token");
		}
		return token;
	}

	/**
	 * 获取int值，为空赋予默认值
	 *
	 * @param request
	 * @param name
	 * @param defaultStr
	 * @return
	 */
	public static Integer getIntegerToDefault(HttpServletRequest request, String name, String defaultStr)
			throws ParamFormatException {
		String str = getStringToDefault(request, name, defaultStr);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		Integer result = null;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			throw new ParamFormatException(name);
		}
		return result;
	}

	public static BigDecimal getDecimalNotNull(HttpServletRequest request, String name) throws ParamsNullException {
		BigDecimal decimal = getDecimal(request, name);
		if (decimal == null) {
			throw new ParamsNullException(name);
		}
		return decimal;
	}

	public static BigDecimal getDecimal(HttpServletRequest request, String name) {
		return getDecimalToDefault(request, name, null);
	}

	public static BigDecimal getDecimalToDefault(HttpServletRequest request, String name, String defaultStr) throws ParamFormatException {
		String stringToDefault = getStringToDefault(request, name, defaultStr);
		if (stringToDefault == null) {
			return null;
		}
		return new BigDecimal(stringToDefault);
	}

	public static Double getDoubleNotNull(HttpServletRequest request, String name) throws ParamsNullException {
		Double param = getDouble(request, name);
		if (param == null) {
			throw new ParamsNullException(name);
		}
		return param;
	}

	public static Double getDouble(HttpServletRequest request, String name) {
		return getDoubleToDefault(request, name, null);
	}

	/**
	 * 获取double值，为空获取默认值
	 *
	 * @param request
	 * @param name
	 * @param defaultStr
	 * @return
	 */
	public static Double getDoubleToDefault(HttpServletRequest request, String name, String defaultStr)
			throws ParamFormatException {
		String str = getStringToDefault(request, name, defaultStr);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		Double result = null;
		try {
			result = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			throw new ParamFormatException(name);
		}
		return result;
	}

	public static Date getDateTimeNotNull(HttpServletRequest request, String name) {
		Date time = getDateTime(request, name, DateUtils.YYYY_MM_DD);
		if (time == null) {
			throw new ParamsNullException(name);
		}
		return time;
	}

	public static Date getDateTime(HttpServletRequest request, String name) {
		return getDateTime(request, name, DateUtils.YYYY_MM_DD);
	}

	public static Date getDateTime(HttpServletRequest request, String name, String pattern) {
		String param = request.getParameter(name);
		if (StringUtils.isBlank(param)) {
			return null;
		}
		if (!DateUtils.formatCorrect(param, pattern)) {
			throw new ParamFormatException(name);
		}
		return DateUtils.str2Date(param, pattern);
	}

	public static String getStringNotNull(HttpServletRequest request, String name) throws ParamsNullException {
		String param = request.getParameter(name);
		if (StringUtils.isBlank(param)) {
			throw new ParamsNullException(name);
		}
		return param;
	}

	/**
	 * 获取string值，为空获取默认值
	 *
	 * @param request
	 * @param name
	 * @param defaultStr
	 * @return
	 */
	public static String getStringToDefault(HttpServletRequest request, String name, String defaultStr) {
		String param = request.getParameter(name);
		if (StringUtils.isBlank(param)) {
			param = defaultStr;
		}
		return param;
	}

	public static void printParams(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap(); // 获取请求里面的参数
		for (String key : map.keySet()) { // 循环参数的键
			String[] values = map.get(key); // 根据键获取参数的值
			String paramKey = StringUtils.leftPad(key, 20, "");
			String paramValue = null;
			for (int i = 0; i < values.length; i++) { // 循环值
				if (paramValue == null) {
					paramValue = values[i];
				} else {
					paramValue = paramValue + "," + values[i];
				}
			}
			log.info("==>{}", (paramKey + ":" + paramValue));
		}
	}

	public static void printJSON(HttpServletResponse response, Object obj) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String result = JSONObject.toJSONString(obj);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public static void printHtml(HttpServletResponse response, String html) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(html);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
