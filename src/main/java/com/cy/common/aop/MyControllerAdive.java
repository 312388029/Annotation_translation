package com.cy.common.aop;

import com.cy.api.vm.ResponseJsonVM;
import com.cy.common.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdive {
	private Logger logger = LoggerFactory.getLogger(MyControllerAdive.class);

	/**
	 * 参数为空异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = ParamsNullException.class)
	public String paremsNull(ParamsNullException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("1010", ex.getMessage());
	}

	/**
	 * 参数转化异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = ParamFormatException.class)
	public String paremsNull(ParamFormatException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("2010", ex.getMessage());
	}

	/**
	 * 数据库数据异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = DatabaseDataException.class)
	public String databaseData(DatabaseDataException ex) {
		logger.error(ex.getMessage(), ex);
		return ResponseJsonVM.fail("3010", ex.getMessage());
	}

	/**
	 * 文件上传异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = UploadException.class)
	public String fileUpload(UploadException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("4010", ex.getMessage());
	}

	/**
	 * 没有权限许可异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = NoPermissionException.class)
	public String notPermission(NoPermissionException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("403", ex.getMessage());
	}

	/**
	 * 服务异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = ServiceException.class)
	public String paremsNull(ServiceException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("9010", ex.getMessage());
	}

	/**
	 * Token认证异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = AuthTokenException.class)
	public String requestMethodNotSupported(AuthTokenException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("5010", ex.getMessage());
	}

	/**
	 * 请求方式异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public String requestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		logger.error(ex.getMessage());
		return ResponseJsonVM.fail("9099", "请求方式有误");
	}

	/**
	 * 系统异常捕获
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public String paremsNull(Exception ex) {
		ex.printStackTrace();
		logger.error(ex.getMessage(), ex);
		return ResponseJsonVM.fail("9999", "系统异常");
	}

}
