package com.desolatetimelines.acct.workspace.model;

import java.time.Instant;

public interface AcctDeposit {

    String getDepositUUID();

    void setDepositUUID(String depositUUID);

    String getDepositAccountNumber();

    void setDepositAccountNumber(String depositAccountNumber);

    String getCurrencyUUID();

    void setCurrencyUUID(String currencyUUID);

    String getBankUUID();

    void setBankUUID(String bankUUID);

    Double getDepositValue();

    void setDepositValue(Double depositValue);

    Double getDepositInterestPercent();

    void setDepositInterestPercent(Double depositInterestPercent);

    AcctAccountRecord getDepositCreationAccountRecord();

    void setDepositCreationAccountRecord(AcctAccountRecord depositCreationAccountRecord);

    AcctAccountRecord getDepositReturnAccountRecord();

    void setDepositReturnAccountRecord(AcctAccountRecord depositReturnAccountRecord);

    AcctAccountRecord getDepositInterestAccountRecord();

    void setDepositInterestAccountRecord(AcctAccountRecord depositInterestAccountRecord);

    Instant getDepositProjectedEndDate();

    void setDepositProjectedEndDate(Instant depositProjectedEndDate);

}
