package com.desolatetimelines.acct.service.dao.model;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;

import java.util.Date;

public interface CurrencyHistoryRecord extends CommonAccountingEntity<CurrencyHistoryRecord> {
	Long getCurrencyId();

	void setCurrencyId(Long currencyId);

	Date getDate();

	void setDate(Date date);

	Double getValue();

	void setValue(Double value);

	@Override
	default void copyFrom(CurrencyHistoryRecord other) {
		if (other == null) {
			return;
		}

		this.setId(other.getId());
		this.setCurrencyId(other.getCurrencyId());
		this.setDate(other.getDate());
		this.setValue(other.getValue());
	}
}
