package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

public class InMemoryInterestHistoryRecord implements InterestHistoryRecord {

	private Long id;

	private Long bankId;

	private Date snapshotDate;

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
	public Long getBankId() {
		return bankId;
	}

	@Override
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Override
	public Date getSnapshotDate() {
		return snapshotDate;
	}

	@Override
	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
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
