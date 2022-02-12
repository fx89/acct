package com.desolatetimelines.acct.service.dao.springdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataMonitoredCurrency;

import java.util.stream.Stream;

public interface SpringDataMonitoredCurrenciesRepository extends CrudRepository<SpringDataMonitoredCurrency, Long> {
	Stream<SpringDataMonitoredCurrency> findAllByCurrencyTypeName(String currencyTypeName);

	SpringDataMonitoredCurrency findOneByCurrencyTypeNameAndBankId(String currencyTypeName, Long bankId);
}
