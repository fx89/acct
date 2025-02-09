package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation related to an income or expense item category occurs
 */
public class AcctCatalogServiceIncomeOrExpenseItemCategoryConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceIncomeOrExpenseItemCategoryConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemCategoryName,
        Throwable cause
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_CATEGORY_ALREADY_EXISTS,
            Map.of(
                "incomeOrExpenseItemCategoryName", incomeOrExpenseItemCategoryName
            ),
            cause
        );
    }

}
