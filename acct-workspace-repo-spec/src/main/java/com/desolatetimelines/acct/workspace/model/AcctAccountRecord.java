package com.desolatetimelines.acct.workspace.model;

import java.time.Instant;

public interface AcctAccountRecord {

    Long getAccountRecordId();

    void setAccountRecordId(Long accountRecordId);

    AcctAccount getAccount();

    void setAccount(AcctAccount account);

    Instant getAccountRecordDate();

    void setAccountRecordDate(Instant accountRecordDate);

    String getRecordedByUserUUID();

    void setRecordedByUserUUID(String recordedByUserUUID);

    String getIncomeOrExpenseItemUUID();

    void setIncomeOrExpenseItemUUID(String incomeOrExpenseItemUUID);

    String getAccountRecordText();

    void setAccountRecordText(String accountRecordText);

    Double getAccountRecordValue();

    void setAccountRecordValue(Double accountRecordValue);

    Instant getLastModifiedDate();

    void setLastModifiedDate(Instant lastModifiedDate);

    String getLastModifiedByUserUUID();

    void setLastModifiedByUserUUID(String lastModifiedByUserUUID);
}
