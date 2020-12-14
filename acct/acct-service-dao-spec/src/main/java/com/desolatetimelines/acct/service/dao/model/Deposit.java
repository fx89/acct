package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;

public interface Deposit extends CommonAccountingEntity<Deposit> {

	Long getBankId();

	void setBankId(Long bankId);

	String getAccountNumber();

	void setAccountNumber(String accountNumber);

	Long getSourceAccountId();

	void setSourceAccountId(Long sourceAccountId);

	Date getStartDate();

	void setStartDate(Date startDate);

	Date getEndDate();

	void setEndDate(Date endDate);

	Double getValue();

	void setValue(Double value);

	Double getInterestPercent();

	void setInterestPercent(Double interestPercent);

	@Override
	default void copyFrom(Deposit other) {
		if (other != null) {
			this.setId(other.getId());
			this.setAccountNumber(other.getAccountNumber());
			this.setBankId(other.getBankId());
			this.setStartDate(other.getStartDate());
			this.setEndDate(other.getEndDate());
			this.setValue(other.getValue());
			this.setInterestPercent(other.getInterestPercent());
			this.setSourceAccountId(other.getSourceAccountId());
		}
	}
}
