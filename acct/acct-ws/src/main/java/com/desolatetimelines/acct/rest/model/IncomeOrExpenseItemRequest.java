package com.desolatetimelines.acct.rest.model;

import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;

public class IncomeOrExpenseItemRequest implements IncomeOrExpenseItem {

	Long id;

	String name;

	Long incomeOrExpenseItemCategoryId;

	Double lastUsedValue;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getIncomeOrExpenseItemCategoryId() {
		return incomeOrExpenseItemCategoryId;
	}

	@Override
	public void setIncomeOrExpenseItemCategoryId(Long categoryId) {
		this.incomeOrExpenseItemCategoryId = categoryId;
	}

	@Override
	public Double getLastUsedValue() {
		return lastUsedValue;
	}

	@Override
	public void setLastUsedValue(Double lastUsedBalue) {
		this.lastUsedValue = lastUsedBalue;
	}

}
