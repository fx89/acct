package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Contains the updatable properties of an income or expense item subcategory
 *
 * @param incomeOrExpenseItemSubcategoryName        the name that uniquely identifies the subcategory in the catalog
 * @param incomeOrExpenseItemSubcategoryDescription a detailed description of what kind of items the subcategory contains
 * @param incomeOrExpenseItemSubcategoryIconUUID    the UUID of the icon that should be displayed in the UI for the subcategory
 */
public record IncomeOrExpenseItemSubcategorySaveRequest(
    String incomeOrExpenseItemSubcategoryName,
    String incomeOrExpenseItemSubcategoryDescription,
    String incomeOrExpenseItemSubcategoryIconUUID
) {
}
