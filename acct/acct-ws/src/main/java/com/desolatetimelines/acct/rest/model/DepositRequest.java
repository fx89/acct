package com.desolatetimelines.acct.rest.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.model.Deposit;

public class DepositRequest implements Deposit {

	private Long id;

	private Long bankId;

	private String accountNumber;

	private Date startDate;

	private Date endDate;

	private Double value;

	private Double interestPercent;

	private Long sourceAccountId;

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
		return bankId;
	}

	@Override
	public void setBankId(Long bankId) {
		this.bankId = bankId;
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

	@Override
	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	@Override
	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

}
