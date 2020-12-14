package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryMonitoredCurrency implements MonitoredCurrency {

	private Long id;

	private String currencyTypeName;

	private Date lastCollectedDate;

	private Double lastCollectedValue;

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

	public Double getLastCollectedValue() {
		return lastCollectedValue;
	}

	public void setLastCollectedValue(Double lastCollectedValue) {
		this.lastCollectedValue = lastCollectedValue;
	}

}
