package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItem;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;

import java.util.Collection;

/**
 * Repository for loading and persisting {@link AcctIncomeOrExpenseItem income or expense items}
 */
public interface AcctIncomeOrExpenseItemsRepository {

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItem income or expense items} contained by the
     * {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories} in the given collection
     * of income or expense item subcategories
     *
     * @param incomeOrExpenseItemSubcategories the given collection of income or expense item subcategories
     */
    Collection<AcctIncomeOrExpenseItem> findAllByIncomeOrExpenseItemSubcategoryIn(
        Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories
    );

    /**
     * Deletes the {@link AcctIncomeOrExpenseItem income or expense items} in the given collection
     * of income or expense items
     *
     * @param incomeOrExpenseItems the given collection of income or expense items
     */
    void deleteAll(Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems);

}
