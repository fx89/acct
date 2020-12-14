package com.desolatetimelines.acct.service.Exception;

public class AccountServiceValidationException extends AccountServiceException {
	public AccountServiceValidationException() {

	}

	public AccountServiceValidationException(String message) {
		super(message);
	}

	public AccountServiceValidationException(String message, Throwable source) {
		super(message, source);
	}
}
