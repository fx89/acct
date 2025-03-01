package com.desolatetimelines.acct.currency.model;

import java.time.Instant;
import java.util.Objects;

public class InMemoryAcctMonitoredCurrency implements AcctMonitoredCurrency {

    private String monitoredCurrencyUUID;

    private String bankUUID;

    private String currencyUUID;

    private String quotedCurrencyUUID;

    private String collectorName;

    private String scheduledTimeHHMM;

    private Instant lastCollectionDate;

    private String collectionErrorMessage;

    private Instant lastMonitoredCurrencyRecordDate;

    private Double lastMonitoredCurrencyRecordPurchaseValue;

    private Double lastMonitoredCurrencyRecordSaleValue;

    public InMemoryAcctMonitoredCurrency() {

    }

    private InMemoryAcctMonitoredCurrency(InMemoryAcctMonitoredCurrencyBuilder builder) {
        setMonitoredCurrencyUUID(builder.monitoredCurrencyUUID);
        setBankUUID(builder.bankUUID);
        setCurrencyUUID(builder.currencyUUID);
        setQuotedCurrencyUUID(builder.quotedCurrencyUUID);
        setCollectorName(builder.collectorName);
        setScheduledTimeHHMM(builder.scheduledTimeHHMM);
        setLastCollectionDate(builder.lastCollectionDate);
        setCollectionErrorMessage(builder.collectionErrorMessage);
        setLastMonitoredCurrencyRecordDate(builder.lastMonitoredCurrencyRecordDate);
        setLastMonitoredCurrencyRecordPurchaseValue(builder.lastMonitoredCurrencyRecordPurchaseValue);
        setLastMonitoredCurrencyRecordSaleValue(builder.lastMonitoredCurrencyRecordSaleValue);
    }

    public static InMemoryAcctMonitoredCurrencyBuilder builder() {
        return new InMemoryAcctMonitoredCurrencyBuilder();
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
    public void setLastMonitoredCurrencyRecordPurchaseValue(Double lastMonitoredCurrencyPurchaseValue) {
        this.lastMonitoredCurrencyRecordPurchaseValue = lastMonitoredCurrencyPurchaseValue;
    }

    @Override
    public Double getLastMonitoredCurrencyRecordSaleValue() {
        return lastMonitoredCurrencyRecordSaleValue;
    }

    @Override
    public void setLastMonitoredCurrencyRecordSaleValue(Double lastMonitoredCurrencySaleValue) {
        this.lastMonitoredCurrencyRecordSaleValue = lastMonitoredCurrencySaleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryAcctMonitoredCurrency that = (InMemoryAcctMonitoredCurrency) o;
        return Objects.equals(monitoredCurrencyUUID, that.monitoredCurrencyUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(monitoredCurrencyUUID);
    }


    public static final class InMemoryAcctMonitoredCurrencyBuilder {
        private String monitoredCurrencyUUID;
        private String bankUUID;
        private String currencyUUID;
        private String quotedCurrencyUUID;
        private String collectorName;
        private String scheduledTimeHHMM;
        private Instant lastCollectionDate;
        private String collectionErrorMessage;
        private Instant lastMonitoredCurrencyRecordDate;
        private Double lastMonitoredCurrencyRecordPurchaseValue;
        private Double lastMonitoredCurrencyRecordSaleValue;

        private InMemoryAcctMonitoredCurrencyBuilder() {
        }

        public InMemoryAcctMonitoredCurrencyBuilder withMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
            this.monitoredCurrencyUUID = monitoredCurrencyUUID;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withQuotedCurrencyUUID(String quotedCurrencyUUID) {
            this.quotedCurrencyUUID = quotedCurrencyUUID;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withCollectorName(String collectorName) {
            this.collectorName = collectorName;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withScheduledTimeHHMM(String scheduledTimeHHMM) {
            this.scheduledTimeHHMM = scheduledTimeHHMM;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withLastCollectionDate(Instant lastCollectionDate) {
            this.lastCollectionDate = lastCollectionDate;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withCollectionErrorMessage(String collectionErrorMessage) {
            this.collectionErrorMessage = collectionErrorMessage;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withLastMonitoredCurrencyRecordDate(Instant lastMonitoredCurrencyRecordDate) {
            this.lastMonitoredCurrencyRecordDate = lastMonitoredCurrencyRecordDate;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withLastMonitoredCurrencyRecordPurchaseValue(Double lastMonitoredCurrencyRecordPurchaseValue) {
            this.lastMonitoredCurrencyRecordPurchaseValue = lastMonitoredCurrencyRecordPurchaseValue;
            return this;
        }

        public InMemoryAcctMonitoredCurrencyBuilder withLastMonitoredCurrencyRecordSaleValue(Double lastMonitoredCurrencyRecordSaleValue) {
            this.lastMonitoredCurrencyRecordSaleValue = lastMonitoredCurrencyRecordSaleValue;
            return this;
        }

        public InMemoryAcctMonitoredCurrency build() {
            return new InMemoryAcctMonitoredCurrency(this);
        }
    }
}
