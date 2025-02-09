package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation related to an income or expense item occurs
 */
public class AcctCatalogServiceIncomeOrExpenseItemConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceIncomeOrExpenseItemConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemName,
        Throwable cause
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_ALREADY_EXISTS,
            Map.of(
                "incomeOrExpenseItemName", incomeOrExpenseItemName
            ),
            cause
        );
    }

}
