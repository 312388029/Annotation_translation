package com.cy.common.exception;

public class ParamFormatException extends NumberFormatException {
	
	private static final long serialVersionUID = 1L;

	public ParamFormatException() {
		super();
	}

	public ParamFormatException(String name) {
		super(name + "格式有误");
	}
}
