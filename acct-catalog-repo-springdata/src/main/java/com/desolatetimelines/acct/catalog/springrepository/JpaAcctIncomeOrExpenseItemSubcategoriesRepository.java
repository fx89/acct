package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemSubcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JpaAcctIncomeOrExpenseItemSubcategoriesRepository extends CrudRepository<JpaAcctIncomeOrExpenseItemSubcategory, Long> {

    Collection<JpaAcctIncomeOrExpenseItemSubcategory> findAllByIncomeExpenseItemCategoryIn(
        Collection<JpaAcctIncomeOrExpenseItemCategory> incomeExpenseItemCategories
    );

}
