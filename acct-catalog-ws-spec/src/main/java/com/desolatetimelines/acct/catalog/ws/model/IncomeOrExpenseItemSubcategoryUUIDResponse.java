package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Wraps the UUID of an income or expense item subcategory inside a container that
 * can be returned from the items endpoint
 */
public record IncomeOrExpenseItemSubcategoryUUIDResponse(
    String incomeOrExpenseItemSubcategoryUUID
) {
}
