package com.cy.common.exception;

/**
 * JSON格式异常
 */
public class JsonFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JsonFormatException() {
		super();
	}

	public JsonFormatException(String msg) {
		super(msg);
	}
}
