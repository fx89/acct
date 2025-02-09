package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an income or expense item category is not found
 */
public class AcctCatalogServiceIncomeOrExpenseItemCategoryNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceIncomeOrExpenseItemCategoryNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemCategoryUUID
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_CATEGORY_NOT_FOUND,
            Map.of("incomeOrExpenseItemCategoryUUID", incomeOrExpenseItemCategoryUUID)
        );
    }

}
