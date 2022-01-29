package com.desolatetimelines.acct.rest.model;

import com.desolatetimelines.acct.service.dao.model.Account;

public class AccountRequest implements Account {

	private Long id;

	private String name;

	private Long currencyId;

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
