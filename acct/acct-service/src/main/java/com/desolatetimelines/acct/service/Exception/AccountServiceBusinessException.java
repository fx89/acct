package com.desolatetimelines.acct.service.Exception;

public class AccountServiceBusinessException extends AccountServiceException {
	public AccountServiceBusinessException(String message) {
		super(message);
	}

	public AccountServiceBusinessException(String message, Throwable source) {
		super(message, source);
	}
}
