import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MonitoredCurrencyCollector } from '../model-acct/monitored-currency-collector';
import { AcctMonitoredCurrenciesRepository } from '../repositories-acct/monitored-currencies-repository';
import { MonitoredCurrency, MonitoredCurrencyProperties } from '../model-acct/monitored-currency-properties';
import { errorPipingObservableOperation, errorPipingObservableTransform } from '../utils-reusalbe/rxjs-utils';
import { MonitoredCurrencyUUIDResponse } from '../model-acct/monitored-currency-uuid-response';

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

  public findAllAvailableMonitoredCurrencyCollectors() : Observable<MonitoredCurrencyCollector[]> {
    return this.monitoredCurrenciesRepository.findAllMonitoredCurrencyCollectors()
  }

  public saveMonitoredCurrency(monitoredCurrency:MonitoredCurrency) : Observable<void> {
    return errorPipingObservableTransform<MonitoredCurrencyUUIDResponse,void>(
      this.monitoredCurrenciesRepository.saveMonitoredCurrency(monitoredCurrency),
      () => {}
    )
  }

  public findAllMonitoredCurrencies() : Observable<MonitoredCurrencyProperties[]> {
    return this.monitoredCurrenciesRepository.findAllMonitoredCurrencies()
  }

  public deleteMonitoredCurrency(monitoredCurrency:MonitoredCurrency) : Observable<void> {
    if (monitoredCurrency?.monitoredCurrencyUUID) {
      return this.monitoredCurrenciesRepository.deleteMonitoredCurrency(monitoredCurrency.monitoredCurrencyUUID)
    } else {
      throw new Error("Missing monitored currency UUID")
    }
  }

}
