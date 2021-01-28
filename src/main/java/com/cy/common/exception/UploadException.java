package com.cy.common.exception;

/**
 * 文件上传异常
 *
 */
public class UploadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UploadException() {
		super();
	}

	public UploadException(String msg) {
		super(msg);
	}

}
