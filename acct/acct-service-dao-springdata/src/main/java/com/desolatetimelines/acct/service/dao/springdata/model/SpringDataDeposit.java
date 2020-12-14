package com.desolatetimelines.acct.service.dao.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.Deposit;

@Entity
@Table(name = "deposit")
public class SpringDataDeposit implements Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bank_id")
	private Long bankId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "source_account_id")
	private Long sourceAccountId;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "value")
	private Double value;

	@Column(name = "interest_percent")
	private Double interestPercent;

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
