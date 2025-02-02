package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;

import java.util.Collection;

/**
 * Repository for loading and persisting {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
 */
public interface AcctIncomeOrExpenseItemSubcategoriesRepository {

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * that are contained by the {@link AcctIncomeOrExpenseItemCategory income or expense item categories} in
     * the referenced collection of income or expense item categories
     *
     * @param incomeOrExpenseItemCategories the referenced collection of income or expense item categories
     */
    Collection<AcctIncomeOrExpenseItemSubcategory> findAllByByIncomeOrExpenseItemCategoryIn(
        Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories
    );

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * in the referenced collection of income or expense item subcategories
     *
     * @param incomeOrExpenseItemSubcategories the referenced collection of income or expense item subcategories
     */
    void deleteAll(Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories);

}
