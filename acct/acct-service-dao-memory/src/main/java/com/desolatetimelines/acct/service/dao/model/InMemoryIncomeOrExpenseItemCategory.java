package com.desolatetimelines.acct.service.dao.model;

public class InMemoryIncomeOrExpenseItemCategory implements IncomeOrExpenseItemCategory {

	private Long id;

	private String name;

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

}
