package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItem;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctIncomeOrExpenseItem income or expense items}
 */
public interface AcctIncomeOrExpenseItemsRepository {

    /**
     * Creates a new instance of {@link AcctIncomeOrExpenseItem income or expense item}
     *
     * @return a reference to the created instance
     */
    AcctIncomeOrExpenseItem createNew();

    /**
     * Returns the {@link AcctIncomeOrExpenseItem income or expense item} with the given
     * income or expense item UUID or an empty optional if such an item does not exist.
     *
     * @param incomeOrExpenseItemUUID the given income or expense item UUID
     */
    Optional<AcctIncomeOrExpenseItem> findFirstByIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID);

    /**
     * Returns a collection of the {@link AcctIncomeOrExpenseItem income or expense items}
     * identified by the UUIDs in the given collection of income or expense item UUIDs
     *
     * @param incomeOrExpenseItemUUIDs the given collection of income or expense item UUIDs
     */
    Collection<AcctIncomeOrExpenseItem> findAllByIncomeOrExpenseItemUUIDIn(
        Collection<String> incomeOrExpenseItemUUIDs
    );

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
     * Returns a collection of the {@link AcctIncomeOrExpenseItem income or expense items}
     * linked to the icons identified by the UUIDs in the given collection of icon UUIDs
     *
     * @param iconUUIDs the given collection of income or expense item UUIDs
     */
    Collection<AcctIncomeOrExpenseItem> findAllByIncomeOrExpenseItemIconUUIDIn(Collection<String> iconUUIDs);

    /**
     * Persists the referenced {@link AcctIncomeOrExpenseItem income or expense item}
     *
     * @param incomeOrExpenseItem the referenced income or expense item
     * @return a reference to the persisted entity
     */
    AcctIncomeOrExpenseItem save(AcctIncomeOrExpenseItem incomeOrExpenseItem);

    /**
     * Deletes the {@link AcctIncomeOrExpenseItem income or expense items} in the given collection
     * of income or expense items
     *
     * @param incomeOrExpenseItems the given collection of income or expense items
     */
    void deleteAll(Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems);

}
