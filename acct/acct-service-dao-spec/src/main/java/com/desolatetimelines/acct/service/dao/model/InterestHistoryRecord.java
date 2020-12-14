package com.desolatetimelines.acct.service.dao.model;

import java.util.Date;

import com.desolatetimelines.acct.service.dao.common.CommonAccountingEntity;

public interface InterestHistoryRecord extends CommonAccountingEntity<InterestHistoryRecord> {
	Long getBankId();

	void setBankId(Long bankId);

	Date getSnapshotDate();

	void setSnapshotDate(Date snapshotDate);

	Double getValue();

	void setValue(Double value);

	@Override
	default void copyFrom(InterestHistoryRecord other) {
		this.setId(other.getId());
		this.setBankId(other.getBankId());
		this.setSnapshotDate(other.getSnapshotDate());
		this.setValue(other.getValue());
	}
}
