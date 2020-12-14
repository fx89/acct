package com.desolatetimelines.acct.service.currency.exception;

public class BnrCurrencyExtractorException extends CurrencyExtractorException {
	public BnrCurrencyExtractorException(String message) {
		super(message);
	}

	public BnrCurrencyExtractorException(String message, Throwable source) {
		super(message, source);
	}
}
