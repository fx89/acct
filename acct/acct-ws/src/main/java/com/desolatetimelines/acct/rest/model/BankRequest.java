package com.desolatetimelines.acct.rest.model;

import com.desolatetimelines.acct.service.dao.model.Bank;

public class BankRequest implements Bank {

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
