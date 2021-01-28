package com.cy.common.exception;

/**
 * @author Yjie
 */
public class AuthTokenException extends RuntimeException {
	public AuthTokenException() {
	}

	public AuthTokenException(String message) {
		super(message);
	}
}
