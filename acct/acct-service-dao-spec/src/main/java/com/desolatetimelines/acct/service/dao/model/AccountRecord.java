  package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;

public interface AccountRecord extends CommonAccountingEntity<AccountRecord> {

	void setId(Long id);

	Long getAccountId();

	void setAccountId(Long accountId);

	Date getDate();

	void setDate(Date date);

	Long getIncomeOrExpenseItemId();

	void setIncomeOrExpenseItemId(Long incomeOrExpenseItemId);

	Double getValue();

	void setValue(Double value);

	@Override
	default void copyFrom(AccountRecord other) {
		if (other != null) {
			this.setId(other.getId());
			this.setAccountId(other.getAccountId());
			this.setDate(other.getDate());
			this.setIncomeOrExpenseItemId(other.getIncomeOrExpenseItemId());
			this.setValue(other.getValue());
		}
	}
}
