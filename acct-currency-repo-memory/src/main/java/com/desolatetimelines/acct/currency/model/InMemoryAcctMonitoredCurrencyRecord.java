package com.desolatetimelines.acct.currency.model;

import java.time.Instant;
import java.util.Objects;

public class InMemoryAcctMonitoredCurrencyRecord implements AcctMonitoredCurrencyRecord {

    private AcctMonitoredCurrency monitoredCurrency;

    private Instant monitoredCurrencyRecordDate;

    private Double monitoredCurrencyRecordPurchaseValue;

    private Double monitoredCurrencyRecordSaleValue;

    public InMemoryAcctMonitoredCurrencyRecord() {

    }

    private InMemoryAcctMonitoredCurrencyRecord(InMemoryAcctMonitoredCurrencyRecordBuilder builder) {
        setMonitoredCurrency(builder.monitoredCurrency);
        setMonitoredCurrencyRecordDate(builder.monitoredCurrencyRecordDate);
        setMonitoredCurrencyRecordPurchaseValue(builder.monitoredCurrencyRecordPurchaseValue);
        setMonitoredCurrencyRecordSaleValue(builder.monitoredCurrencyRecordSaleValue);
    }

    public static InMemoryAcctMonitoredCurrencyRecordBuilder builder() {
        return new InMemoryAcctMonitoredCurrencyRecordBuilder();
    }

    @Override
    public AcctMonitoredCurrency getMonitoredCurrency() {
        return monitoredCurrency;
    }

    @Override
    public void setMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
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
        return monitoredCurrencyRecordSaleValue;
    }

    @Override
    public void setMonitoredCurrencyRecordSaleValue(Double monitoredCurrencyRecordSaleValue) {
        this.monitoredCurrencyRecordSaleValue = monitoredCurrencyRecordSaleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryAcctMonitoredCurrencyRecord that = (InMemoryAcctMonitoredCurrencyRecord) o;
        return Objects.equals(monitoredCurrency, that.monitoredCurrency) && Objects.equals(monitoredCurrencyRecordDate, that.monitoredCurrencyRecordDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitoredCurrency, monitoredCurrencyRecordDate);
    }


    public static final class InMemoryAcctMonitoredCurrencyRecordBuilder {
        private AcctMonitoredCurrency monitoredCurrency;
        private Instant monitoredCurrencyRecordDate;
        private Double monitoredCurrencyRecordPurchaseValue;
        private Double monitoredCurrencyRecordSaleValue;

        private InMemoryAcctMonitoredCurrencyRecordBuilder() {
        }

        public InMemoryAcctMonitoredCurrencyRecordBuilder withMonitoredCurrency(AcctMonitoredCurrency monitoredCurrency) {
            this.monitoredCurrency = monitoredCurrency;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordDate(Instant monitoredCurrencyRecordDate) {
            this.monitoredCurrencyRecordDate = monitoredCurrencyRecordDate;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordPurchaseValue(Double monitoredCurrencyRecordPurchaseValue) {
            this.monitoredCurrencyRecordPurchaseValue = monitoredCurrencyRecordPurchaseValue;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordSaleValue(Double monitoredCurrencyRecordSaleValue) {
            this.monitoredCurrencyRecordSaleValue = monitoredCurrencyRecordSaleValue;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyRecord build() {
            return new InMemoryAcctMonitoredCurrencyRecord(this);
        }
    }
}
