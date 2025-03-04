package com.desolatetimelines.acct.currency.ws.model;

import java.time.Instant;

/**
 * Container for the readable properties of a monitored currency record
 *
 * @param monitoredCurrencyRecordDate
 * @param monitoredCurrencyRecordPurchaseValue
 * @param monitoredCurrencyRecordSaleValue
 */
public record MonitoredCurrencyRecordProperties(
    Instant monitoredCurrencyRecordDate,
    Double monitoredCurrencyRecordPurchaseValue,
    Double monitoredCurrencyRecordSaleValue
) {
    public static MonitoredCurrencyRecordPropertiesBuilder builder() {
        return new MonitoredCurrencyRecordPropertiesBuilder();
    }

    public static final class MonitoredCurrencyRecordPropertiesBuilder {
        private Instant monitoredCurrencyRecordDate;
        private Double monitoredCurrencyRecordPurchaseValue;
        private Double monitoredCurrencyRecordSaleValue;

        private MonitoredCurrencyRecordPropertiesBuilder() {
        }

        public MonitoredCurrencyRecordPropertiesBuilder withMonitoredCurrencyRecordDate(Instant monitoredCurrencyRecordDate) {
            this.monitoredCurrencyRecordDate = monitoredCurrencyRecordDate;
            return this;
        }

        public MonitoredCurrencyRecordPropertiesBuilder withMonitoredCurrencyRecordPurchaseValue(Double monitoredCurrencyRecordPurchaseValue) {
            this.monitoredCurrencyRecordPurchaseValue = monitoredCurrencyRecordPurchaseValue;
            return this;
        }

        public MonitoredCurrencyRecordPropertiesBuilder withMonitoredCurrencyRecordSaleValue(Double monitoredCurrencyRecordSaleValue) {
            this.monitoredCurrencyRecordSaleValue = monitoredCurrencyRecordSaleValue;
            return this;
        }

        public MonitoredCurrencyRecordProperties build() {
            return
                new MonitoredCurrencyRecordProperties(
                    monitoredCurrencyRecordDate,
                    monitoredCurrencyRecordPurchaseValue,
                    monitoredCurrencyRecordSaleValue
                );
        }
    }
}
