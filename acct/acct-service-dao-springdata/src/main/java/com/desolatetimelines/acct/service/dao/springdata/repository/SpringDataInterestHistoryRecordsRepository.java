package com.desolatetimelines.acct.service.dao.springdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataInterestHistoryRecord;

public interface SpringDataInterestHistoryRecordsRepository
		extends CrudRepository<SpringDataInterestHistoryRecord, Long> {

	Iterable<SpringDataInterestHistoryRecord> findAllByBankId(Long bankId);
}
