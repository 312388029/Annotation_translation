package com.cy.common.exception;

/**
 * 传入参数为空值异常
 *
 */
public class ParamsNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParamsNullException() {
		super();
	}

	public ParamsNullException(String name) {
		super(name + "不能为空");
	}

}
