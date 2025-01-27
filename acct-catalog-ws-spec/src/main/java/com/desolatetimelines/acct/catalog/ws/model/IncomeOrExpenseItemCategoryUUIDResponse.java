package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Wraps the UUID of an income or expense item category inside a container that
 * can be returned from the items endpoint
 */
public record IncomeOrExpenseItemCategoryUUIDResponse(
    String incomeOrExpenseItemCategoryUUID
) {
}
