package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryDeposit implements Deposit {

	private Long id;

	private Long BankId;

	private String accountNumber;

	private Long sourceAccountId;

	private Date startDate;

	private Date endDate;

	private Double value;

	private Double interestPercent;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getBankId() {
		return BankId;
	}

	@Override
	public void setBankId(Long bankId) {
		BankId = bankId;
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	@Override
	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public Double getInterestPercent() {
		return interestPercent;
	}

	@Override
	public void setInterestPercent(Double interestPercent) {
		this.interestPercent = interestPercent;
	}

}
