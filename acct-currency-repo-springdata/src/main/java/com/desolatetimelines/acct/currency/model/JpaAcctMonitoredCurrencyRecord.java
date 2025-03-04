package com.desolatetimelines.acct.currency.model;

import jakarta.persistence.*;

import java.time.Instant;

import static com.desolatetimelines.acct.currency.util.AcctCurrencyRepoSpringDataUtils.doWithJpaAcctMonitoredCurrencyReturning;
import static java.util.function.Function.identity;

@Entity
@Table(name = "monitored_currency_record")
public class JpaAcctMonitoredCurrencyRecord implements AcctMonitoredCurrencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitored_currency_record_id")
    private Long monitoredCurrencyRecordId;

    @ManyToOne
    @JoinColumn(name = "monitored_currency_id")
    private JpaAcctMonitoredCurrency monitoredCurrency;

    @Column(name = "monitored_currency_record_date")
    private Instant monitoredCurrencyRecordDate;

    @Column(name = "monitored_currency_record_purchase_value")
    private Double monitoredCurrencyRecordPurchaseValue;

    @Column(name = "monitored_currency_record_sale_value")
    private Double monitoredCurrencySaleValue;

    public Long getMonitoredCurrencyRecordId() {
        return monitoredCurrencyRecordId;
    }

    public void setMonitoredCurrencyRecordId(Long monitoredCurrencyRecordId) {
        this.monitoredCurrencyRecordId = monitoredCurrencyRecordId;
    }

    @Override
    public AcctMonitoredCurrency getMonitoredCurrency() {
        return monitoredCurrency;
    }

    @Override
    public void setMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
        this.monitoredCurrency = doWithJpaAcctMonitoredCurrencyReturning(monitoredCurrency, identity());
    }

    public void setMonitoredCurrency(JpaAcctMonitoredCurrency monitoredCurrency) {
        this.monitoredCurrency = monitoredCurrency;
    }

    @Override
    public Instant getMonitoredCurrencyRecordDate() {
        return monitoredCurrencyRecordDate;
    }

    @Override
    public void setMonitoredCurrencyRecordDate(Instant monitoredCurrencyRecordDate) {
        this.monitoredCurrencyRecordDate = monitoredCurrencyRecordDate;
    }

    @Override
    public Double getMonitoredCurrencyRecordPurchaseValue() {
        return monitoredCurrencyRecordPurchaseValue;
    }

    @Override
    public void setMonitoredCurrencyRecordPurchaseValue(Double monitoredCurrencyRecordPurchaseValue) {
        this.monitoredCurrencyRecordPurchaseValue = monitoredCurrencyRecordPurchaseValue;
    }

    @Override
    public Double getMonitoredCurrencyRecordSaleValue() {
        return monitoredCurrencySaleValue;
    }

    @Override
    public void setMonitoredCurrencyRecordSaleValue(Double monitoredCurrencySaleValue) {
        this.monitoredCurrencySaleValue = monitoredCurrencySaleValue;
    }
}
