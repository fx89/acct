import { Observable } from "rxjs";
import { MonitoredCurrency, MonitoredCurrencyProperties } from "../model-acct/monitored-currency-properties";
import { MonitoredCurrencyUUIDResponse } from "../model-acct/monitored-currency-uuid-response";
import { MonitoredCurrencyCollector } from "../model-acct/monitored-currency-collector";
import { MonitoredCurrencyRecord } from "../model-acct/monitored-currency-record";

/**
 * Allows creating, reading, updating and deleting monitored currencies
 */
export abstract class AcctMonitoredCurrenciesRepository {

    /**
     * Saves the referenced monitored currency
     * 
     * @param monitoredCurrency the referenced monitored currency
     */
    abstract saveMonitoredCurrency(monitoredCurrency : MonitoredCurrency) : Observable<MonitoredCurrencyUUIDResponse>

    /**
     * Returns an observable that produces an array of monitored currency properties for all
     * the registered monitored currencies
     */
    abstract findAllMonitoredCurrencies() : Observable<MonitoredCurrencyProperties[]>

    /**
     * Returns an observable that produces an array of monitored currency collectors
     */
    abstract findAllMonitoredCurrencyCollectors() : Observable<MonitoredCurrencyCollector[]>

    /**
     * Returns the monitored currency records for the monitored currency identified by the given
     * monitored currency UUID, sorted by record date
     */
    abstract findMonitoredCurrencyRecords(monitoredCurrencyUUID:string) : Observable<MonitoredCurrencyRecord[]>

    /**
     * Deletes the monitored currency with the given monitored currency UUID
     * 
     * @param monitoredCurrencyUUID The given monitored currency UUID
     */
    abstract deleteMonitoredCurrency(monitoredCurrencyUUID:string) : Observable<void>

    /**
     * Runs the collector for the monitored currency specified by the given monitored currency UUID
     * 
     * @param monitoredCurrencyUUID The given monitored currency UUID
     */
    abstract collectManually(monitoredCurrencyUUID:string) : Observable<void>

    /**
     * Adds or updates the given monitored currency records for the monitored currency referenced by
     * the given monitored currency UUID
     * 
     * @param monitoredCurrencyUUID The given monitored currency UUID
     * @param records               The given monitored currency records
     */
    abstract addMonitoredCurrencyRecords(
        monitoredCurrencyUUID:string,
        records:MonitoredCurrencyRecord[]
    ) : Observable<void>

}