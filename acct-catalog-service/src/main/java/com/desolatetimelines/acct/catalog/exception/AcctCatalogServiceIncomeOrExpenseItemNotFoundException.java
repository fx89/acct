package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Generic exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when an income or expense item is not found
 */
public class AcctCatalogServiceIncomeOrExpenseItemNotFoundException extends AcctCatalogServiceNotFoundException {

    public AcctCatalogServiceIncomeOrExpenseItemNotFoundException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemUUID
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_NOT_FOUND,
            Map.of("incomeOrExpenseItemUUID", incomeOrExpenseItemUUID)
        );
    }

}
