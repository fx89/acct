package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
 */
public interface AcctIncomeOrExpenseItemCategoriesRepository {

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     */
    AcctIncomeOrExpenseItemCategory createNew();

    /**
     * Returns a reference to the {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     * with the given income or expense item category UUID or an empty optional if such a category does
     * not exist
     *
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item category UUID
     */
    Optional<AcctIncomeOrExpenseItemCategory> findFirstByIncomeOrExpenseItemCategoryUUID(
        String incomeOrExpenseItemCategoryUUID
    );

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItemCategory income or expense item category}
     *
     * @param incomeOrExpenseItemCategory the referenced income or expense item category
     * @return a reference to the persisted entity
     */
    AcctIncomeOrExpenseItemCategory save(
        AcctIncomeOrExpenseItemCategory incomeOrExpenseItemCategory
    );

    /**
     * Returns a collection of all the {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     */
    Collection<AcctIncomeOrExpenseItemCategory> findAll();

    /**
     * Returns a collection of all {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     * for the income or expense item category UUIDs contined by the given collection
     *
     * @param incomeOrExpenseItemCategoryUUIDs the given collection
     */
    Collection<AcctIncomeOrExpenseItemCategory> findByIncomeOrExpenseItemCategoryUUIDIn(
        Collection<String> incomeOrExpenseItemCategoryUUIDs
    );

    /**
     * Returns a collection of {@link AcctIncomeOrExpenseItemSubcategory income or expense item categories}
     * linked to the {@link AcctIcon icons} identified by the UUIDs in the given collection icon UUIDs
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    Collection<AcctIncomeOrExpenseItemCategory> findAllByIncomeOrExpenseItemCategoryIconUUIDIn(
        Collection<String> iconUUIDs
    );

    /**
     * Deletes all the referenced {@link AcctIncomeOrExpenseItemCategory income or expense item categories}
     *
     * @param incomeOrExpenseItemCategories the referenced income or expense item categories
     */
    void deleteAll(Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories);
}
