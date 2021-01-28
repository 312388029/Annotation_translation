package com.cy.common.exception;

/**
 * 数据库数据异常
 */
public class DatabaseDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseDataException() {
		super();
	}

	public DatabaseDataException(String name) {
		super(name + "数据异常");
	}
}
