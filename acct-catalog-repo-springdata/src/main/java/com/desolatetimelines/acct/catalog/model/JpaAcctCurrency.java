package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "currency")
public class JpaAcctCurrency implements AcctCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;

    @Column(name = "currency_uuid")
    private String currencyUUID;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "currency_icon_uuid")
    private String currencyIconUUID;


    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String getCurrencyUUID() {
        return currencyUUID;
    }

    @Override
    public void setCurrencyUUID(String currencyUUID) {
        this.currencyUUID = currencyUUID;
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String getCurrencyName() {
        return currencyName;
    }

    @Override
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String getCurrencyIconUUID() {
        return currencyIconUUID;
    }

    @Override
    public void setCurrencyIconUUID(String currencyIconUUID) {
        this.currencyIconUUID = currencyIconUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctCurrency that = (JpaAcctCurrency) o;
        return Objects.equals(currencyUUID, that.currencyUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currencyUUID);
    }
}
