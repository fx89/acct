package com.desolatetimelines.acct.catalog.model;

/**
 * Groups {@link AcctIncomeOrExpenseItem income or expense items} of the same type to help reporting
 */
public interface AcctIncomeOrExpenseItemSubcategory {

    AcctIncomeOrExpenseItemCategory getIncomeOrExpenseItemCategory();

    void setIncomeOrExpenseItemCategory(AcctIncomeOrExpenseItemCategory incomeOrExpenseItemCategory);

    String getIncomeOrExpenseItemSubcategoryUUID();

    void setIncomeOrExpenseItemSubcategoryUUID(String incomeOrExpenseItemSubcategoryUUID);

    String getIncomeOrExpenseItemSubcategoryName();

    void setIncomeOrExpenseItemSubcategoryName(String incomeOrExpenseItemSubcategoryName);

    String getIncomeOrExpenseItemSubcategoryDescription();

    void setIncomeOrExpenseItemSubcategoryDescription(String incomeOrExpenseItemSubcategoryDescription);

    String getIncomeOrExpenseItemSubcategoryIconUUID();

    void setIncomeOrExpenseItemSubcategoryIconUUID(String incomeOrExpenseItemSubcategoryIconUUID);

}
