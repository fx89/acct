package com.desolatetimelines.acct.service.dao.model;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;
import com.desolatetimelines.acct.service.dao.common.Nameable;

public interface IncomeOrExpenseItemCategory extends CommonAccountingEntity<IncomeOrExpenseItemCategory>, Nameable {

	@Override
	default void copyFrom(IncomeOrExpenseItemCategory other) {
		this.setId(other.getId());
		this.setName(other.getName());
	}
}
