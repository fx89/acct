package com.desolatetimelines.acct.service.dao.springdata.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataAccountRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpringDataAccountRecordsRepository extends PagingAndSortingRepository<SpringDataAccountRecord, Long> {

	Iterable<SpringDataAccountRecord> findAllByAccountId(Long accountId);

	Page<SpringDataAccountRecord> findAllByAccountId(Long accountId, Pageable pageable);

	Iterable<SpringDataAccountRecord> findAllByAccountIdAndDateGreaterThanEqual(Long accountId, Date date);


}
