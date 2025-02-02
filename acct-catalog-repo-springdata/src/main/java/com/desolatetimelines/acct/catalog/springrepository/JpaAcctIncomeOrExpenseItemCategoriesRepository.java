package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctIncomeOrExpenseItemCategoriesRepository
    extends CrudRepository<JpaAcctIncomeOrExpenseItemCategory, Long> {

    Optional<JpaAcctIncomeOrExpenseItemCategory> findFirstByIncomeExpenseItemCategoryUUID(
        String incomeOrExpenseItemCategoryUUID
    );

    Collection<JpaAcctIncomeOrExpenseItemCategory> findByIncomeExpenseItemCategoryUUIDIn(
        Collection<String> incomeOrExpenseItemCategoryUUIDs
    );
}
