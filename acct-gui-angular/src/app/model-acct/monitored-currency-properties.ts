/**
 * Container for the readable properties of a monitored currency
 */
export interface MonitoredCurrency {

    /**
     * Unique identifier for the monitored currency
     */
    monitoredCurrencyUUID? : string

    /**
     * UUID of the bank that offers the exchange rate
     */
    bankUUID : string

    /**
     * UUID of the currency for which the exchange rate is provided
     */
    currencyUUID : string

    /**
     * UUID of the currency against which the exchange rate is measured
     */
    quotedCurrencyUUID : string

    /**
     * Class name of the exchange rates collector service
     */
    collectorName : string

    /**
     * Time of day, formatted as "HH:MM", after which it's safe to collect the day's exchange rate
     */
    scheduledTimeHhMm : string

}

/**
 * Extends the MonitoredCurrency interface with properties related to the
 * latest data collection
 */
export interface MonitoredCurrencyProperties extends MonitoredCurrency {

    /**
     * The date and time when the last exchange rate value was collected
     */
    lastMonitoredCurrencyRecordDate : string

    /**
     * The last collected purchase value
     */
    lastMonitoredCurrencyRecordPurchaseValue : number

    /**
     * The last collected sale value (quoted currency vs currency)
     */
    lastMonitoredCurrencyRecordSaleValue : number

}