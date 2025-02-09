package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.*;

import java.util.Collection;

/**
 * Defines operations that are supported by the Catalog service for items,
 * item categories and item sub-categories
 */
public interface ItemsEndpoint {

    /**
     * Creates or updates an income or expense items category with the properties in
     * the given request. The decision to create or update is made based on the existence
     * of the given income or expense item category UUID. If it is not given then a
     * new income or expense item category is created. If it is given then the income
     * or expense item category with the given UUID is updated (if found).
     *
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item category UUID
     * @param request                         the given request
     * @return a container for the UUID of the saved income or expense item category
     */
    IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItemCategory(
        String incomeOrExpenseItemCategoryUUID,
        IncomeOrExpenseItemCategorySaveRequest request
    );

    /**
     * Returns a collection of all the income or expense item categories in the catalog
     */
    Collection<IncomeOrExpenseItemCategoryProperties> getIncomeOrExpenseItemCategories();

    /**
     * Deletes the income or expense item categories represented by the UUIDs in the given collection
     * of income or expense item category UUIDs. Throws exceptions if any of the categories is not found
     * or cannot be deleted.
     *
     * @param incomeOrExpenseItemCategoryUUIDs the given list of income or expense item UUIDs
     */
    void deleteIncomeOrExpenseItemCategories(Collection<String> incomeOrExpenseItemCategoryUUIDs);

    /**
     * Creates or updates an income or expense items subcategory with the properties in
     * the given request. The decision to create or update is made based on the existence
     * of the given income or expense item subcategory UUID. If it is not given then a
     * new income or expense item category is created. If it is given then the income
     * or expense item category with the given UUID is updated (if found).
     *
     * @param incomeOrExpenseItemCategoryUUID    the parent category if the subcategory being saved
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     * @param request                            the given request
     * @return a container for the UUID of the saved income or expense item subcategory
     */
    IncomeOrExpenseItemSubcategoryUUIDResponse saveIncomeOrExpenseItemSubcategory(
        String incomeOrExpenseItemCategoryUUID,
        String incomeOrExpenseItemSubcategoryUUID,
        IncomeOrExpenseItemSubcategorySaveRequest request
    );

    /**
     * Returns a collection of all the income or expense item subcategories belonging to the
     * category referenced by the given income or expense item category UUID in the catalog
     *
     * @param incomeOrExpenseItemCategoryUUID the given income or expense item UUID
     */
    Collection<IncomeOrExpenseItemSubcategoryProperties> getIncomeOrExpenseItemSubcategories(
        String incomeOrExpenseItemCategoryUUID
    );

    /**
     * Deletes the income or expense item subcategories represented by the UUIDs in the given collection
     * of income or expense item subcategory UUIDs. Throws exceptions if any of the subcategories is not
     * found or cannot be deleted.
     *
     * @param incomeOrExpenseItemSubcategoryUUIDs the given list of income or expense item UUIDs
     */
    void deleteIncomeOrExpenseItemSubcategories(Collection<String> incomeOrExpenseItemSubcategoryUUIDs);

    /**
     * Persists an income or expense item with the properties found in the given request within the income
     * or expense items subcategory with the given income or expense item subcategory UUID. If an income or
     * expense item UUID is given then the income or expense item having the given UUID is updated. If an
     * income or expense item UUID is not given then a new one is created.
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     * @param incomeOrExpenseItemUUID            the given income or expense item UUID
     * @param request                            the given request
     * @return an object containing the income or expense item UUID of the persisted entity
     */
    IncomeOrExpenseItemUUIDResponse saveIncomeOrExpenseItem(
        String incomeOrExpenseItemSubcategoryUUID,
        String incomeOrExpenseItemUUID,
        IncomeOrExpenseItemSaveRequest request
    );

    /**
     * Retrieves a collection of objects containing the properties of the income or expense items
     * that can be found within the income or expense item subcategory identified by the given
     * income or expense item subcategory UUID. Throw an exception if the referenced income or
     * expense item subcategory cannot be found.
     *
     * @param incomeOrExpenseItemSubcategoryUUID the given income or expense item subcategory UUID
     */
    Collection<IncomeOrExpenseItemProperties> getIncomeOrExpenseItems(String incomeOrExpenseItemSubcategoryUUID);

    /**
     * Deletes the income or expense items identified by the UUIDs inside the given collection of
     * income or expense item UUIDs. If any of the referenced items does not exist, an exception
     * is thrown.
     *
     * @param incomeOrExpenseItemUUIDs the given collection of income or expense item UUIDs
     */
    void deleteIncomeOrExpenseItems(Collection<String> incomeOrExpenseItemUUIDs);

}
