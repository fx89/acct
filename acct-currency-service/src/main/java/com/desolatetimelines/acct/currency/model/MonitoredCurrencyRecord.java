package com.desolatetimelines.acct.currency.model;

import java.time.Instant;

public record MonitoredCurrencyRecord(
    Instant monitoredCurrencyRecordDate,
    Double monitoredCurrencyRecordPurchaseValue,
    Double monitoredCurrencyRecordSaleValue
) {
    public static MonitoredCurrencyRecordBuilder builder() {
        return new MonitoredCurrencyRecordBuilder();
    }

    public static final class MonitoredCurrencyRecordBuilder {
        private Instant monitoredCurrencyRecordDate;
        private Double monitoredCurrencyRecordPurchaseValue;
        private Double monitoredCurrencyRecordSaleValue;

        private MonitoredCurrencyRecordBuilder() {
        }

        public MonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordDate(Instant monitoredCurrencyRecordDate) {
            this.monitoredCurrencyRecordDate = monitoredCurrencyRecordDate;
            return this;
        }

        public MonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordPurchaseValue(Double monitoredCurrencyRecordPurchaseValue) {
            this.monitoredCurrencyRecordPurchaseValue = monitoredCurrencyRecordPurchaseValue;
            return this;
        }

        public MonitoredCurrencyRecordBuilder withMonitoredCurrencyRecordSaleValue(Double monitoredCurrencyRecordSaleValue) {
            this.monitoredCurrencyRecordSaleValue = monitoredCurrencyRecordSaleValue;
            return this;
        }

        public MonitoredCurrencyRecord build() {
            return
                new MonitoredCurrencyRecord(
                    monitoredCurrencyRecordDate,
                    monitoredCurrencyRecordPurchaseValue,
                    monitoredCurrencyRecordSaleValue
                );
        }
    }
}
