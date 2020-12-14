package com.desolatetimelines.acct.service.dao.springdata.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataAccountRecord;

public interface SpringDataAccountRecordsRepository extends CrudRepository<SpringDataAccountRecord, Long> {

	Iterable<SpringDataAccountRecord> findAllByAccountId(Long accountId);

	Iterable<SpringDataAccountRecord> findAllByAccountIdAndDateGreaterThanEqual(Long accountId, Date date);
}
