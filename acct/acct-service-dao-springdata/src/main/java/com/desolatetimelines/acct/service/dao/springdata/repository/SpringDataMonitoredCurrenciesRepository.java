package com.desolatetimelines.acct.service.dao.springdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataMonitoredCurrency;

public interface SpringDataMonitoredCurrenciesRepository extends CrudRepository<SpringDataMonitoredCurrency, Long> {
	SpringDataMonitoredCurrency findOneByCurrencyTypeName(String currencyTypeName);
}
