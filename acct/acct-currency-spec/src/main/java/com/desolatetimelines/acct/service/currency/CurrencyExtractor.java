package com.desolatetimelines.acct.service.currency;

import java.util.List;

import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;

public interface CurrencyExtractor {
	List<CurrencyExtractorHistoryRecord> fetchLatestRecords(CurrencyType currencyType)
			throws CurrencyExtractorException;
}
