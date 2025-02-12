package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bank")
public class JpaAcctBank implements AcctBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "bank_uuid")
    private String bankUUID;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "internet_banking_url")
    private String internetBankingURL;

    @Column(name = "bank_icon_uuid")
    private String bankIconUUID;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Override
    public String getBankUUID() {
        return bankUUID;
    }

    @Override
    public void setBankUUID(String bankUUID) {
        this.bankUUID = bankUUID;
    }

    @Override
    public String getBankCode() {
        return bankCode;
    }

    @Override
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String getInternetBankingURL() {
        return internetBankingURL;
    }

    @Override
    public void setInternetBankingURL(String internetBankingURL) {
        this.internetBankingURL = internetBankingURL;
    }

    @Override
    public String getBankIconUUID() {
        return bankIconUUID;
    }

    @Override
    public void setBankIconUUID(String bankIconUUID) {
        this.bankIconUUID = bankIconUUID;
    }

}
