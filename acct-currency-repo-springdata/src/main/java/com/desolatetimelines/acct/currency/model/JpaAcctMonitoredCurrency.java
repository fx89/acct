package com.desolatetimelines.acct.currency.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "monitored_currency")
public class JpaAcctMonitoredCurrency implements AcctMonitoredCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitored_currency_id")
    private Long monitoredCurrencyId;

    @Column(name = "monitored_currency_uuid")
    private String monitoredCurrencyUUID;

    @Column(name = "bank_uuid")
    private String bankUUID;

    @Column(name = "currency_uuid")
    private String currencyUUID;

    @Column(name = "quoted_currency_uuid")
    private String quotedCurrencyUUID;

    @Column(name = "collector_name")
    private String collectorName;

    @Column(name = "scheduled_time_hh_mm")
    private String scheduledTimeHHMM;

    @Column(name = "last_collection_date")
    private Instant lastCollectionDate;

    @Column(name = "collection_error_message")
    private String collectionErrorMessage;

    @Column(name = "last_monitored_currency_record_date")
    private Instant lastMonitoredCurrencyRecordDate;

    @Column(name = "last_monitored_currency_record_purchase_value")
    private Double lastMonitoredCurrencyRecordPurchaseValue;

    @Column(name = "last_monitored_currency_record_sale_value")
    private Double lastMonitoredCurrencyRecordSaleValue;

    public Long getMonitoredCurrencyId() {
        return monitoredCurrencyId;
    }

    public void setMonitoredCurrencyId(Long monitoredCurrencyId) {
        this.monitoredCurrencyId = monitoredCurrencyId;
    }

    @Override
    public String getMonitoredCurrencyUUID() {
        return monitoredCurrencyUUID;
    }

    @Override
    public void setMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
        this.monitoredCurrencyUUID = monitoredCurrencyUUID;
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
    public String getCurrencyUUID() {
        return currencyUUID;
    }

    @Override
    public void setCurrencyUUID(String currencyUUID) {
        this.currencyUUID = currencyUUID;
    }

    @Override
    public String getQuotedCurrencyUUID() {
        return quotedCurrencyUUID;
    }

    @Override
    public void setQuotedCurrencyUUID(String quotedCurrencyUUID) {
        this.quotedCurrencyUUID = quotedCurrencyUUID;
    }

    @Override
    public String getCollectorName() {
        return collectorName;
    }

    @Override
    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    @Override
    public String getScheduledTimeHHMM() {
        return scheduledTimeHHMM;
    }

    @Override
    public void setScheduledTimeHHMM(String scheduledTimeHHMM) {
        this.scheduledTimeHHMM = scheduledTimeHHMM;
    }

    @Override
    public Instant getLastCollectionDate() {
        return lastCollectionDate;
    }

    @Override
    public void setLastCollectionDate(Instant lastCollectionDate) {
        this.lastCollectionDate = lastCollectionDate;
    }

    @Override
    public String getCollectionErrorMessage() {
        return collectionErrorMessage;
    }

    @Override
    public void setCollectionErrorMessage(String collectionErrorMessage) {
        this.collectionErrorMessage = collectionErrorMessage;
    }

    @Override
    public Instant getLastMonitoredCurrencyRecordDate() {
        return lastMonitoredCurrencyRecordDate;
    }

    @Override
    public void setLastMonitoredCurrencyRecordDate(Instant lastMonitoredCurrencyRecordDate) {
        this.lastMonitoredCurrencyRecordDate = lastMonitoredCurrencyRecordDate;
    }

    @Override
    public Double getLastMonitoredCurrencyRecordPurchaseValue() {
        return lastMonitoredCurrencyRecordPurchaseValue;
    }

    @Override
    public void setLastMonitoredCurrencyRecordPurchaseValue(Double lastMonitoredCurrencyRecordPurchaseValue) {
        this.lastMonitoredCurrencyRecordPurchaseValue = lastMonitoredCurrencyRecordPurchaseValue;
    }

    @Override
    public Double getLastMonitoredCurrencyRecordSaleValue() {
        return lastMonitoredCurrencyRecordSaleValue;
    }

    @Override
    public void setLastMonitoredCurrencyRecordSaleValue(Double lastMonitoredCurrencyRecordSaleValue) {
        this.lastMonitoredCurrencyRecordSaleValue = lastMonitoredCurrencyRecordSaleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctMonitoredCurrency that = (JpaAcctMonitoredCurrency) o;
        return Objects.equals(monitoredCurrencyUUID, that.monitoredCurrencyUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(monitoredCurrencyUUID);
    }
}
