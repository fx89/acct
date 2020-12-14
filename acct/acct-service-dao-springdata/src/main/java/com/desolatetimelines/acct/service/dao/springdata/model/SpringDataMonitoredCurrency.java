package com.desolatetimelines.acct.service.dao.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;

@Entity
@Table(name = "monitored_currency")
public class SpringDataMonitoredCurrency implements MonitoredCurrency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "currency_type_name")
	private String currencyTypeName;

	@Column(name = "last_collected_date")
	private Date lastCollectedDate;

	@Column(name = "last_collected_value")
	private Double lastCollectedValue;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCurrencyTypeName() {
		return currencyTypeName;
	}

	@Override
	public void setCurrencyTypeName(String currencyTypeName) {
		this.currencyTypeName = currencyTypeName;
	}

	@Override
	public Date getLastCollectedDate() {
		return lastCollectedDate;
	}

	@Override
	public void setLastCollectedDate(Date lastCollectedDate) {
		this.lastCollectedDate = lastCollectedDate;
	}

	@Override
	public Double getLastCollectedValue() {
		return lastCollectedValue;
	}

	@Override
	public void setLastCollectedValue(Double lastCollectedValue) {
		this.lastCollectedValue = lastCollectedValue;
	}

}
