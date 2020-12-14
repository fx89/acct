package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryCurrencyHistoryRecord implements CurrencyHistoryRecord {

	private Long id;

	private Long currencyId;

	private Date date;

	private Double value;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getCurrencyId() {
		return currencyId;
	}

	@Override
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

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
