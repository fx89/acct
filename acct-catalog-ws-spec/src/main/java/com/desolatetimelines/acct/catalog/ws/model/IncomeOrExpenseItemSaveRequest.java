package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Contains the modifiable properties of an income or expense item
 *
 * @param incomeOrExpenseItemName        The human-readable unique name of the income or expense item
 * @param incomeOrExpenseItemDescription The human-readable description of the income or expense item
 * @param incomeOrExpenseItemIconUUID    The UUID that uniquely identifies the icon that represents the income or expense item
 */
public record IncomeOrExpenseItemSaveRequest(
    String incomeOrExpenseItemName,
    String incomeOrExpenseItemDescription,
    String incomeOrExpenseItemIconUUID
) {
}
