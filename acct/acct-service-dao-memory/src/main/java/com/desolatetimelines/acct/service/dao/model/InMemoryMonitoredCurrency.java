package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryMonitoredCurrency implements MonitoredCurrency {

	private Long id;

	private String currencyTypeName;

	private Date lastCollectedDate;

	private Double lastCollectedValue;

	private Long bankId;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCurrencyTypeName() {
		return currencyTypeName;
	}

	@Override
	public void setCurrencyTypeName(String currencyTypeName) {
		this.currencyTypeName = currencyTypeName;
	}

	@Override
	public Date getLastCollectedDate() {
		return lastCollectedDate;
	}

	@Override
	public void setLastCollectedDate(Date lastCollectedDate) {
		this.lastCollectedDate = lastCollectedDate;
	}

	@Override
	public Double getLastCollectedValue() {
		return lastCollectedValue;
	}

	@Override
	public void setLastCollectedValue(Double lastCollectedValue) {
		this.lastCollectedValue = lastCollectedValue;
	}

	@Override
	public Long getBankId() {
		return bankId;
	}

	@Override
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}


}
