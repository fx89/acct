package com.desolatetimelines.acct.service.dao.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.Account;

@Entity
@Table(name = "account")
public class SpringDataAccount implements Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "currency_id")
	private Long currencyId;

	@Column(name = "is_foreign_currency")
	private Boolean isForeignCurrencyAccount;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getCurrencyId() {
		return currencyId;
	}

	@Override
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	@Override
	public Boolean isForeignCurrencyAccount() {
		return isForeignCurrencyAccount;
	}

	@Override
	public void setForeignCurrencyAccount(Boolean isForeignCurrencyAccount) {
		this.isForeignCurrencyAccount = isForeignCurrencyAccount;
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
