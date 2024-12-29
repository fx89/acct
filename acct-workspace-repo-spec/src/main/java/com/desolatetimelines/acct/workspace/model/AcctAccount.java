package com.desolatetimelines.acct.workspace.model;

public interface AcctAccount {

    String getAccountUUID();

    void setAccountUUID(String accountUUID);

    AcctWorkspace getWorkspace();

    void setWorkspace(AcctWorkspace workspace);

    String getAccountName();

    void setAccountName(String accountName);

    String getAccountIconUUID();

    void setAccountIconUUID(String accountIconUUID);

    String getAccountNumber();

    void setAccountNumber(String accountNumber);

    String getCurrencyUUID();

    void setCurrencyUUID(String currencyUUID);

    String getBankUUID();

    void setBankUUID(String bankUUID);

}
