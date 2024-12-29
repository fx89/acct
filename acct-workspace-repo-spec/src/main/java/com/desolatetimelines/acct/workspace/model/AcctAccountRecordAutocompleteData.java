package com.desolatetimelines.acct.workspace.model;

public interface AcctAccountRecordAutocompleteData {

    AcctAccount getAccount();

    void setAccount(AcctAccount account);

    String getIncomeOrExpenseItemUUID();

    void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID);

    String getAccountRecordText();

    void setAccountRecordText(String accountRecordText);

    Double getLastUsedAccountRecordValue();

    void setLastUsedAccountRecordValue(Double lastUsedAccountRecordValue);

}
