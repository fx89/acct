package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;

public interface MonitoredCurrency extends CommonAccountingEntity<MonitoredCurrency> {
	String getCurrencyTypeName();

	void setCurrencyTypeName(String currencyTypeName);

	Date getLastCollectedDate();

	void setLastCollectedDate(Date lastCollectedDate);

	Double getLastCollectedValue();

	void setLastCollectedValue(Double lastCollectedValue);

	@Override
	public default void copyFrom(MonitoredCurrency other) {
		if (other == null) {
			return;
		}

		this.setId(other.getId());
		this.setCurrencyTypeName(other.getCurrencyTypeName());
		this.setLastCollectedDate(other.getLastCollectedDate());
		this.setLastCollectedValue(other.getLastCollectedValue());
	}
}
