package com.desolatetimelines.acct.service.dao.model;

public class InMemoryIncomeOrExpenseItem implements IncomeOrExpenseItem {

	private Long id;

	private String name;

	private Long incomeOrExpenseItemCategoryId;

	private Double lastUsedValue;

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
	public void setLastUsedValue(Double lastUsedValue) {
		this.lastUsedValue = lastUsedValue;
	}

}
