package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryAccountRecord implements AccountRecord {

	private Long id;

	private Long accountId;

	private Date date;

	private Long incomeOrExpenseItemId;

	private Double value;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getAccountId() {
		return accountId;
	}

	@Override
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Long getIncomeOrExpenseItemId() {
		return incomeOrExpenseItemId;
	}

	@Override
	public void setIncomeOrExpenseItemId(Long incomeOrExpenseItemId) {
		this.incomeOrExpenseItemId = incomeOrExpenseItemId;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

}
