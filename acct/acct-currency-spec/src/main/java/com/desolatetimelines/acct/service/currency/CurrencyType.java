package com.desolatetimelines.acct.service.currency;

import java.util.Set;

public enum CurrencyType {

	EUR("EUR"), USD("USD"), RON("RON"), GBP("GBP"), CHF("CHF");

	public static final Set<CurrencyType> ALL = Set.of(EUR, USD, RON, GBP, CHF);

	private final String value;

	private CurrencyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
