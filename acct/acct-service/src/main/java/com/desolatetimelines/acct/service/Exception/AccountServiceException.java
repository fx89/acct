package com.desolatetimelines.acct.service.Exception;

public class AccountServiceException extends AccountingAppException {
	public AccountServiceException() {

	}

	public AccountServiceException(String message) {
		super(message);
	}

	public AccountServiceException(String message, Throwable source) {
		super(message, source);
	}
}
