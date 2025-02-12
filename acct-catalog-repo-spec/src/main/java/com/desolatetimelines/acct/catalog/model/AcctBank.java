package com.desolatetimelines.acct.catalog.model;

/**
 * Uniquely identifies a bank in the ACCT catalog
 */
public interface AcctBank {

    String getBankUUID();

    void setBankUUID(String bankUUID);

    String getBankCode();

    void setBankCode(String bankCode);

    String getBankName();

    void setBankName(String bankName);

    String getInternetBankingURL();

    void setInternetBankingURL(String internetBankingURL);

    String getBankIconUUID();

    void setBankIconUUID(String bankIconUUID);

}
