import { Observable } from "rxjs";
import { MonitoredCurrencyCollector } from "../../model-acct/monitored-currency-collector";
import { MonitoredCurrency, MonitoredCurrencyProperties } from "../../model-acct/monitored-currency-properties";
import { MonitoredCurrencyRecord } from "../../model-acct/monitored-currency-record";
import { MonitoredCurrencyUUIDResponse } from "../../model-acct/monitored-currency-uuid-response";
import { AcctMonitoredCurrenciesRepository } from "../monitored-currencies-repository";

/**
 * Mock implementation of the AcctMonitoredCurrenciesRepository
 */
export class MockAcctMonitoredCurrenciesRepository extends AcctMonitoredCurrenciesRepository {

    override saveMonitoredCurrency(monitoredCurrency: MonitoredCurrency): Observable<MonitoredCurrencyUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    override findAllMonitoredCurrencies(): Observable<MonitoredCurrencyProperties[]> {
        throw new Error("Method not implemented.");
    }

    override findAllMonitoredCurrencyCollectors(): Observable<MonitoredCurrencyCollector[]> {
        throw new Error("Method not implemented.");
    }

    override findMonitoredCurrencyRecords(monitoredCurrencyUUID: string): Observable<MonitoredCurrencyRecord[]> {
        throw new Error("Method not implemented.");
    }

    override deleteMonitoredCurrency(monitoredCurrencyUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override collectManually(monitoredCurrencyUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override addMonitoredCurrencyRecords(monitoredCurrencyUUID: string, records: MonitoredCurrencyRecord[]): Observable<void> {
        throw new Error("Method not implemented.");
    }
    
}