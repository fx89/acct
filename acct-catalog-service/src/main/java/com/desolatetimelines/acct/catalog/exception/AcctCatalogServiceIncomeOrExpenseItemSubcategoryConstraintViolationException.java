package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Map;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when a constraint violation related to an income or expense item subcategory occurs
 */
public class AcctCatalogServiceIncomeOrExpenseItemSubcategoryConstraintViolationException extends AcctCatalogServiceConstraintViolationException {

    public AcctCatalogServiceIncomeOrExpenseItemSubcategoryConstraintViolationException(
        AcctCatalogErrorCodesRegistryService errors,
        String incomeOrExpenseItemSubcategoryName,
        Throwable cause
    ) {
        super(
            errors.INCOME_OR_EXPENSE_ITEM_SUBCATEGORY_ALREADY_EXISTS,
            Map.of(
                "incomeOrExpenseItemSubcategoryName", incomeOrExpenseItemSubcategoryName
            ),
            cause
        );
    }

}
