package com.cy.common.exception;

/**
 * 没有权限许可
 *
 * @author Yjie
 */
public class NoPermissionException extends RuntimeException {

	public NoPermissionException() {
		super("No permission");
	}

	public NoPermissionException(String message) {
		super(message);
	}
}
