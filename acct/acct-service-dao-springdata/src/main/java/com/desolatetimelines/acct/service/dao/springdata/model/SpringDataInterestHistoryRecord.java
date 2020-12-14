package com.desolatetimelines.acct.service.dao.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;

@Entity
@Table(name = "interest_history_record")
public class SpringDataInterestHistoryRecord implements InterestHistoryRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bank_id")
	private Long bankId;

	@Column(name = "snapshot_date")
	private Date snapshotDate;

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
