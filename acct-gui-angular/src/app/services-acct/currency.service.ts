import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MonitoredCurrencyCollector } from '../model-acct/monitored-currency-collector';
import { AcctMonitoredCurrenciesRepository } from '../repositories-acct/monitored-currencies-repository';
import { MonitoredCurrency, MonitoredCurrencyProperties } from '../model-acct/monitored-currency-properties';
import { errorPipingObservableOperation, errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { MonitoredCurrencyUUIDResponse } from '../model-acct/monitored-currency-uuid-response';
import { MonitoredCurrencyRecord } from '../model-acct/monitored-currency-record';

/**
 * Provides access to functions of the currency service
 */
@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(
    private monitoredCurrenciesRepository : AcctMonitoredCurrenciesRepository
  ) { }

  /**
   * Returns an observable that produces an array of monitored currency collectors
   */
  public findAllAvailableMonitoredCurrencyCollectors() : Observable<MonitoredCurrencyCollector[]> {
    return this.monitoredCurrenciesRepository.findAllMonitoredCurrencyCollectors()
  }

  /**
   * Saves the referenced monitored currency
   * 
   * @param monitoredCurrency the referenced monitored currency
   */
  public saveMonitoredCurrency(monitoredCurrency:MonitoredCurrency) : Observable<void> {
    return errorPipingObservableTransform<MonitoredCurrencyUUIDResponse,void>(
      this.monitoredCurrenciesRepository.saveMonitoredCurrency(monitoredCurrency),
      () => {}
    )
  }

  /**
   * Returns an observable that produces an array of monitored currency properties for all
   * the registered monitored currencies
   */
  public findAllMonitoredCurrencies() : Observable<MonitoredCurrencyProperties[]> {
    return this.monitoredCurrenciesRepository.findAllMonitoredCurrencies()
  }

  /**
   * Deletes the referenced monitored currency
   * 
   * @param monitoredCurrency the referenced monitored currency
   */
  public deleteMonitoredCurrency(monitoredCurrency:MonitoredCurrency) : Observable<void> {
    if (monitoredCurrency?.monitoredCurrencyUUID) {
      return this.monitoredCurrenciesRepository.deleteMonitoredCurrency(monitoredCurrency.monitoredCurrencyUUID)
    } else {
      throw new Error("Missing monitored currency UUID")
    }
  }

  /**
   * Runs the collector for the referenced monitored currency
   * 
   * @param monitoredCurrency the referenced monitored currency
   */
  public manuallyCollectMonitoredCurrencyExchangeRates(monitoredCurrency:MonitoredCurrency) : Observable<void> {
    if (monitoredCurrency?.monitoredCurrencyUUID) {
      return this.monitoredCurrenciesRepository.collectManually(monitoredCurrency.monitoredCurrencyUUID)
    } else {
      throw new Error("Missing monitored currency UUID")
    }
  }

  /**
   * Adds or updates the given monitored currency records for the refernced monitored currency
   * 
   * @param monitoredCurrency the referenced monitored currency
   * @param records           the given monitored currency records
   */
  public addMonitoredCurrencyRecords(
    monitoredCurrency:MonitoredCurrency,
    records:MonitoredCurrencyRecord[]
  ) : Observable<void> {
    if (monitoredCurrency?.monitoredCurrencyUUID) {
      return this.monitoredCurrenciesRepository.addMonitoredCurrencyRecords(
        monitoredCurrency.monitoredCurrencyUUID,
        records
      )
    } else {
      throw new Error("Missing monitored currency UUID")
    }
  }

}
