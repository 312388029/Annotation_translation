package com.cy.common.exception;

/**
 *	自定义服务异常
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
