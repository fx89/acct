package com.desolatetimelines.acct.service.Exception;

public class AccountingAppException extends RuntimeException {
	public AccountingAppException() {

	}

	public AccountingAppException(String message) {
		super(message);
	}

	AccountingAppException(String message, Throwable source) {
		super(message, source);
	}
}
