package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemSubcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctIncomeOrExpenseItemSubcategoriesRepository extends CrudRepository<JpaAcctIncomeOrExpenseItemSubcategory, Long> {

    Collection<JpaAcctIncomeOrExpenseItemSubcategory> findAllByIncomeExpenseItemCategoryIn(
        Collection<JpaAcctIncomeOrExpenseItemCategory> incomeExpenseItemCategories
    );

    Optional<JpaAcctIncomeOrExpenseItemSubcategory> findFirstByIncomeExpenseItemSubcategoryUUID(
        String incomeExpenseItemSubcategoryUUID
    );

    Collection<JpaAcctIncomeOrExpenseItemSubcategory> findByIncomeExpenseItemSubcategoryUUIDIn(
        Collection<String> incomeExpenseItemSubcategoryUUIDs
    );

}
