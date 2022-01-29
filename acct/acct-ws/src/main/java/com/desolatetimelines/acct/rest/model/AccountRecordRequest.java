package com.desolatetimelines.acct.rest.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.model.AccountRecord;

public class AccountRecordRequest implements AccountRecord {

	private Long id;

	private Long accountId;

	private Date date;

	private Long incomeOrExpenseItemId;

	private Double value;

	private Double exchangeRate;

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

	@Override
	public Double getExchangeRate() {
		return exchangeRate;
	}

	@Override
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
