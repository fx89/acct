package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an income or expense item subcategory is not found
 */
public class AcctCatalogServiceIncomeOrExpenseItemSubcategoryNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceIncomeOrExpenseItemSubcategoryNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemSubcategoryUUID
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_NOT_FOUND,
            Map.of("incomeOrExpenseItemSubcategoryUUID", incomeOrExpenseItemSubcategoryUUID)
        );
    }

}
