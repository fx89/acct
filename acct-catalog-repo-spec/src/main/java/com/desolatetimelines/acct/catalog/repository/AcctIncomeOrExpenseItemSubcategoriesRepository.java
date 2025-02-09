package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
 */
public interface AcctIncomeOrExpenseItemSubcategoriesRepository {

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItemSubcategory}
     *
     * @return a reference to the newly created entity
     */
    AcctIncomeOrExpenseItemSubcategory createNew();

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     *
     * @param incomeOrExpenseItemSubcategory the referenced income or expense item subcategory
     * @return a reference to the persisted entity
     */
    AcctIncomeOrExpenseItemSubcategory save(AcctIncomeOrExpenseItemSubcategory incomeOrExpenseItemSubcategory);

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
     * Returns a reference to the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategory}
     * with the given income or expense item subcategory UUID or an empty optional if such a subcategory does
     * not exist
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     */
    Optional<AcctIncomeOrExpenseItemSubcategory> findFirstByIncomeOrExpenseItemSubcategoryUUID(
        String incomeOrExpenseItemSubcategoryUUID
    );

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * for the UUIDs in the given collection of income or expense item subcategory UUIDs
     *
     * @param incomeOrExpenseItemSubcategoryUUIDs the given collection of income or expense item subcategory UUIDs
     */
    Collection<AcctIncomeOrExpenseItemSubcategory> findByIncomeOrExpenseItemSubcategoryUUIDIn(
        Collection<String> incomeOrExpenseItemSubcategoryUUIDs
    );

    /**
     * Deletes the {@link AcctIncomeOrExpenseItemSubcategory income or expense item subcategories}
     * in the referenced collection of income or expense item subcategories
     *
     * @param incomeOrExpenseItemSubcategories the referenced collection of income or expense item subcategories
     */
    void deleteAll(Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories);

}
