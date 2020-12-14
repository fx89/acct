package com.desolatetimelines.acct.service.dao.springdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.desolatetimelines.acct.service.dao.springdata.model.SpringDataIncomeOrExpenseItemCategory;

public interface SpringDataIncomeOrExpenseItemCategoriesRepository
		extends CrudRepository<SpringDataIncomeOrExpenseItemCategory, Long> {

}
