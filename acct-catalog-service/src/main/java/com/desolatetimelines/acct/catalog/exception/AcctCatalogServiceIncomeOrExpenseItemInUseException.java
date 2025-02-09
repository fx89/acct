package com.desolatetimelines.acct.catalog.exception;

import com.desolatetimelines.acct.catalog.service.AcctCatalogErrorCodesRegistryService;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.join;

/**
 * Exception thrown by the {@link com.desolatetimelines.acct.catalog.service.AcctCatalogService catalog service}
 * when one or more income or expense items are in use
 */
public class AcctCatalogServiceIncomeOrExpenseItemInUseException extends AcctCatalogServiceInUseException {

    public AcctCatalogServiceIncomeOrExpenseItemInUseException(
        AcctCatalogErrorCodesRegistryService errors,
        Collection<String> incomeOrExpenseItemUUIDs
    ) {
        super(errors.INCOME_OR_EXPENSE_ITEM_IN_USE, Map.of("incomeOrExpenseItemUUIDs", join(incomeOrExpenseItemUUIDs, ",")));
    }

}
