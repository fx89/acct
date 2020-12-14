package com.desolatetimelines.acct.service.currency.model;

import java.util.Date;

import com.desolatetimelines.acct.service.currency.CurrencyExtractorHistoryRecord;

public class BnrCurrencyExtractorHistoryRecord implements CurrencyExtractorHistoryRecord {
	private Date date;

	private Double value;

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

}
