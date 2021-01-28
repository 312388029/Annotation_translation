package com.cy.api.errors;

import java.io.Serializable;

/**
 * 参数错误描述View Model
 */
public class ParameterizedErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String message;
	private final String[] params;

	public ParameterizedErrorVM(String message, String... params) {
		this.message = message;
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public String[] getParams() {
		return params;
	}

}