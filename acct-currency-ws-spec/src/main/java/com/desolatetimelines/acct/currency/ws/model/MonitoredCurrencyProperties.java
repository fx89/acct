package com.desolatetimelines.acct.currency.ws.model;

import java.time.Instant;

/**
 * Container for the readable properties of a monitored currency
 *
 * @param monitoredCurrencyUUID                    Unique identifier for the monitored currency
 * @param bankUUID                                 UUID of the bank that offers the exchange rate
 * @param currencyUUID                             UUID of the currency for which the exchange rate is provided
 * @param quotedCurrencyUUID                       UUID of the currency against which the exchange rate is measured
 * @param collectorName                            Class name of the exchange rates collector service
 * @param scheduledTimeHhMm                        Time of day, formatted as "HH:MM", after which it's safe to collect the day's exchange rate
 * @param lastMonitoredCurrencyRecordDate          The date and time when the last exchange rate value was collected
 * @param lastMonitoredCurrencyRecordPurchaseValue The last collected purchase value
 * @param lastMonitoredCurrencyRecordSaleValue     The last collected sale value (quoted currency vs currency)
 */
public record MonitoredCurrencyProperties(
    String monitoredCurrencyUUID,
    String bankUUID,
    String currencyUUID,
    String quotedCurrencyUUID,
    String collectorName,
    String scheduledTimeHhMm,
    Instant lastMonitoredCurrencyRecordDate,
    Double lastMonitoredCurrencyRecordPurchaseValue,
    Double lastMonitoredCurrencyRecordSaleValue
) {
    public static MonitoredCurrencyPropertiesBuilder builder() {
        return new MonitoredCurrencyPropertiesBuilder();
    }

    public static final class MonitoredCurrencyPropertiesBuilder {
        private String monitoredCurrencyUUID;
        private String bankUUID;
        private String currencyUUID;
        private String quotedCurrencyUUID;
        private String collectorName;
        private String scheduledTimeHhMm;
        private Instant lastMonitoredCurrencyRecordDate;
        private Double lastMonitoredCurrencyRecordPurchaseValue;
        private Double lastMonitoredCurrencyRecordSaleValue;

        private MonitoredCurrencyPropertiesBuilder() {
        }

        public MonitoredCurrencyPropertiesBuilder withMonitoredCurrencyUUID(String monitoredCurrencyUUID) {
            this.monitoredCurrencyUUID = monitoredCurrencyUUID;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withBankUUID(String bankUUID) {
            this.bankUUID = bankUUID;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withCurrencyUUID(String currencyUUID) {
            this.currencyUUID = currencyUUID;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withQuotedCurrencyUUID(String quotedCurrencyUUID) {
            this.quotedCurrencyUUID = quotedCurrencyUUID;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withCollectorName(String collectorName) {
            this.collectorName = collectorName;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withScheduledTimeHhMm(String scheduledTimeHhMm) {
            this.scheduledTimeHhMm = scheduledTimeHhMm;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withLastMonitoredCurrencyRecordDate(Instant lastMonitoredCurrencyRecordDate) {
            this.lastMonitoredCurrencyRecordDate = lastMonitoredCurrencyRecordDate;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withLastMonitoredCurrencyRecordPurchaseValue(Double lastMonitoredCurrencyRecordPurchaseValue) {
            this.lastMonitoredCurrencyRecordPurchaseValue = lastMonitoredCurrencyRecordPurchaseValue;
            return this;
        }

        public MonitoredCurrencyPropertiesBuilder withLastMonitoredCurrencyRecordSaleValue(Double lastMonitoredCurrencyRecordSaleValue) {
            this.lastMonitoredCurrencyRecordSaleValue = lastMonitoredCurrencyRecordSaleValue;
            return this;
        }

        public MonitoredCurrencyProperties build() {
            return
                new MonitoredCurrencyProperties(
                    monitoredCurrencyUUID,
                    bankUUID,
                    currencyUUID,
                    quotedCurrencyUUID,
                    collectorName,
                    scheduledTimeHhMm,
                    lastMonitoredCurrencyRecordDate,
                    lastMonitoredCurrencyRecordPurchaseValue,
                    lastMonitoredCurrencyRecordSaleValue
                );
        }
    }
}
