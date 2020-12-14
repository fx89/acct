package com.desolatetimelines.acct.service.dao.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;

@Entity
@Table(name = "currency_history_record")
public class SpringDataCurrencyHistoryRecord implements CurrencyHistoryRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "value")
	private Double value;

	@Column(name = "currency_id")
	private Long currencyId;

	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Long getCurrencyId() {
		return currencyId;
	}

	@Override
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

}
