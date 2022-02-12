package com.desolatetimelines.acct.service.dao.model;

public class InMemoryAccount implements Account {
	private Long id;

	private String name;

	Long currencyId;

	Boolean isForeignCurrencyAccount;

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
}
