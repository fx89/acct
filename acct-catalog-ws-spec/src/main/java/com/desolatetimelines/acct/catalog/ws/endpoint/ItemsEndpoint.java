package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryProperties;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategorySaveRequest;
import com.desolatetimelines.acct.catalog.ws.model.IncomeOrExpenseItemCategoryUUIDResponse;

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
     * @return a container for the UUID of the saved income or expense item
     */
    IncomeOrExpenseItemCategoryUUIDResponse saveIncomeOrExpenseItem(
        String incomeOrExpenseItemCategoryUUID,
        IncomeOrExpenseItemCategorySaveRequest request
    );

    /**
     * Returns a collection of all the income or expense item categories in the catalog
     */
    Collection<IncomeOrExpenseItemCategoryProperties> getIncomeOrExpenseItemCategories();

}
