package com.desolatetimelines.acct.service.dao.springdata.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataCurrencyHistoryRecord;

public interface SpringDataCurrencyHistoryRecordsRepository
		extends CrudRepository<SpringDataCurrencyHistoryRecord, Long> {

	void deleteAllByCurrencyId(Long currencyId);

	void deleteAllByCurrencyIdAndDateGreaterThanEqual(Long currencyId, Date sinceDate);

	Iterable<SpringDataCurrencyHistoryRecord> findAllByCurrencyIdAndDateGreaterThanEqual(Long currencyId, Date date);
}
