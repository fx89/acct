package com.desolatetimelines.acct.service.dao.model;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;
import com.desolatetimelines.acct.service.dao.common.Nameable;

public interface IncomeOrExpenseItem extends CommonAccountingEntity<IncomeOrExpenseItem>, Nameable {

	void setId(Long id);

	Long getIncomeOrExpenseItemCategoryId();

	void setIncomeOrExpenseItemCategoryId(Long categoryId);

	void setLastUsedValue(Double lastUsedValue);

	Double getLastUsedValue();

	@Override
	default void copyFrom(IncomeOrExpenseItem other) {
		if (other != null) {
			this.setId(other.getId());
			this.setIncomeOrExpenseItemCategoryId(other.getIncomeOrExpenseItemCategoryId());
			this.setName(other.getName());
			this.setLastUsedValue(other.getLastUsedValue());
		}
	}
}
