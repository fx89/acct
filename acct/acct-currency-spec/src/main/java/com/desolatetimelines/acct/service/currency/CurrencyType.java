package com.desolatetimelines.acct.service.currency;

public enum CurrencyType {

	EUR("EUR"), USD("USD"), RON("RON"), GBP("GBP"), CHF("CHF");

	private String value;

	private CurrencyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
