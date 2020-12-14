package com.desolatetimelines.acct.service.dao.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.AccountRecord;

@Entity
@Table(name = "account_record")
public class SpringDataAccountRecord implements AccountRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "date")
	private Date date;

	@Column(name = "income_or_expense_item_id")
	private Long incomeOrExpenseItemId;

	@Column(name = "value")
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
