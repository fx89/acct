package com.desolatetimelines.acct.service.currency.exception;

public class CurrencyExtractorException extends Exception {

	public CurrencyExtractorException() {
		super();
	}

	public CurrencyExtractorException(String message) {
		super(message);
	}

	public CurrencyExtractorException(String message, Throwable source) {
		super(message, source);
	}
}
