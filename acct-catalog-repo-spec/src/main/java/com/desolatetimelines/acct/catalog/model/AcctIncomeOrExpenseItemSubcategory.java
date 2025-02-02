package com.desolatetimelines.acct.catalog.model;

/**
 * Groups {@link AcctIncomeOrExpenseItem income or expense items} of the same type to help reporting
 */
public interface AcctIncomeOrExpenseItemSubcategory {

    AcctIncomeOrExpenseItemCategory getIncomeExpenseItemCategory();

    void setIncomeOrExpenseItemCategory(AcctIncomeOrExpenseItemCategory incomeExpenseItemCategory);

    String getIncomeOrExpenseItemSubcategoryUUID();

    void setIncomeOrExpenseItemSubcategoryUUID(String incomeExpenseItemSubcategoryUUID);

    String getIncomeOrExpenseItemSubcategoryName();

    void setIncomeOrExpenseItemSubcategoryName(String incomeExpenseItemSubcategoryName);

    String getIncomeOrExpenseItemSubcategoryDescription();

    void setIncomeOrExpenseItemSubcategoryDescription(String incomeExpenseItemSubcategoryDescription);

    String getIncomeOrExpenseItemSubcategoryIconUUID();

    void setIncomeOrExpenseItemSubcategoryIconUUID(String incomeExpenseItemSubcategoryIconUUID);

}
