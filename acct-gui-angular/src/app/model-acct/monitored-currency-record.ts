/**
 * Container for the readable properties of a monitored currency record
 */
export interface MonitoredCurrencyRecord {

    /**
     * The date when the the record was created
     */
    monitoredCurrencyRecordDate : Date

    /**
     * The purchase price, relative to the quoted currency, registered for the currency
     * on the date when the record was created
     */
    monitoredCurrencyRecordPurchaseValue : number

    /**
     * The sale price, relative to the quoted currency, registered for the currency
     * on the date when the record was created
     */
    monitoredCurrencyRecordSaleValue : number

}