package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Contains the updatable properties of an income or expense item category
 *
 * @param incomeOrExpenseItemCategoryName        the name that uniquely identifies the category in the catalog
 * @param incomeOrExpenseItemCategoryDescription a detailed description of what kind of items the category contains
 * @param incomeOrExpenseItemCategoryIconUUID    the UUID of the icon that should be displayed in the UI for the category
 */
public record IncomeOrExpenseItemCategorySaveRequest(
    String incomeOrExpenseItemCategoryName,
    String incomeOrExpenseItemCategoryDescription,
    String incomeOrExpenseItemCategoryIconUUID
) {
}
