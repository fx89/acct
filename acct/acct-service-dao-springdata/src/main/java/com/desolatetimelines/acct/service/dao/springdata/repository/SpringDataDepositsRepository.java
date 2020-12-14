package com.desolatetimelines.acct.service.dao.springdata.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataDeposit;

public interface SpringDataDepositsRepository extends CrudRepository<SpringDataDeposit, Long> {

	Iterable<SpringDataDeposit> findAllByEndDateGreaterThanEqual(Date endDate);

}
