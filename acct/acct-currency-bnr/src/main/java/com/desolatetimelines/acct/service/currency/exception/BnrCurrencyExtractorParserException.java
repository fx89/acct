package com.desolatetimelines.acct.service.currency.exception;

public class BnrCurrencyExtractorParserException extends BnrCurrencyExtractorException {
	public BnrCurrencyExtractorParserException(String message) {
		super(message);
	}

	public BnrCurrencyExtractorParserException(String message, Throwable source) {
		super(message, source);
	}
}
