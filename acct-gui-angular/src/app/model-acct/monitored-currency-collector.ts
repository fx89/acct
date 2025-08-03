/**
 * Defines a currency collector that's available for use for the purpose of
 * gathering records for monitored currencies
 */
export interface MonitoredCurrencyCollector {

    /**
     * The name that uniquely identifies the currency exchange rates collector
     */
    currencyCollectorName : string

    /**
     * A collection of the codes of the banks that are supported by the collector
     */
    supportedBankCodes : string[]

}