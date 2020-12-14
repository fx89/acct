package com.desolatetimelines.acct.service.dao.springdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataIncomeOrExpenseItem;

public interface SpringDataIncomeOrExpenseItemsRepository extends CrudRepository<SpringDataIncomeOrExpenseItem, Long> {

}
