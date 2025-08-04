import { identity, Observable } from "rxjs";
import { MonitoredCurrencyCollector } from "../../model-acct/monitored-currency-collector";
import { MonitoredCurrency, MonitoredCurrencyProperties } from "../../model-acct/monitored-currency-properties";
import { MonitoredCurrencyRecord } from "../../model-acct/monitored-currency-record";
import { MonitoredCurrencyUUIDResponse } from "../../model-acct/monitored-currency-uuid-response";
import { AcctMonitoredCurrenciesRepository } from "../monitored-currencies-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { complete } from "../../utils-reusalbe/rxjs-utils";

/**
 * Implementation of the AcctMonitoredCurrenciesRepository that accesses the back-end services
 */
export class HttpAcctMonitoredCurrenciesRepository extends AcctMonitoredCurrenciesRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override saveMonitoredCurrency(monitoredCurrency: MonitoredCurrency): Observable<MonitoredCurrencyUUIDResponse> {
        return new Observable<MonitoredCurrencyUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If the monitored currency has an UUID, then add it to the parameters object
            if (monitoredCurrency.monitoredCurrencyUUID) {
                params["monitoredCurrencyUUID"] = monitoredCurrency.monitoredCurrencyUUID
            }

            this.httpConnector.put(
                {
                    url: "/monitoredCurrencies",
                    data: {
                        params: params,
                        body: {
                            bankUUID          : monitoredCurrency.bankUUID,
                            currencyUUID      : monitoredCurrency.currencyUUID,
                            quoteCurrencyUUID : monitoredCurrency.quotedCurrencyUUID,
                            collectorName     : monitoredCurrency.collectorName,
                            scheduledTimeHhMm : monitoredCurrency.scheduledTimeHhMm
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Unable to save monitored currency."
                )
            )
        })
    }

    override findAllMonitoredCurrencies(): Observable<MonitoredCurrencyProperties[]> {
        return new Observable<MonitoredCurrencyProperties[]>(subscriber =>
            this.httpConnector.get(
                {
                    url: "/monitoredCurrencies"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Unable to fetch monitored currencies."
                )
            )
        )
    }

    override findAllMonitoredCurrencyCollectors(): Observable<MonitoredCurrencyCollector[]> {
        return new Observable<MonitoredCurrencyCollector[]>(subscriber =>
            this.httpConnector.get(
                {
                    url: "/monitoredCurrencies/collectors"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Unable to fetch monitored currency collectors."
                )
            )
        )
    }

    override findMonitoredCurrencyRecords(monitoredCurrencyUUID: string): Observable<MonitoredCurrencyRecord[]> {
        throw new Error("Method not implemented.");
    }

    override deleteMonitoredCurrency(monitoredCurrencyUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/monitoredCurrencies",
                    data: {
                        params: {
                            monitoredCurrencyUUID: monitoredCurrencyUUID
                        }
                    }
                },
                {
                    responseHandler: () => {
                        complete(subscriber, undefined)
                    },
                    errorHandler: err => {
                        subscriber.error(err)
                    }
                }
            )
        })
    }

    override collectManually(monitoredCurrencyUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/monitoredCurrencies/collectManually",
                    data: {
                        params: {
                            monitoredCurrencyUUID: monitoredCurrencyUUID
                        }
                    }
                },
                {
                    responseHandler: () => {
                        complete(subscriber, undefined)
                    },
                    errorHandler: err => {
                        subscriber.error(err)
                    }
                }
            )
        })
    }

    override addMonitoredCurrencyRecords(monitoredCurrencyUUID: string, records: MonitoredCurrencyRecord[]): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.put(
                {
                    url: "/monitoredCurrencies/records",
                    data: {
                        params: {
                            monitoredCurrencyUUID: monitoredCurrencyUUID
                        },
                        body: records
                    }
                },
                {
                    responseHandler: () => {
                        complete(subscriber, undefined)
                    },
                    errorHandler: err => {
                        subscriber.error(err)
                    }
                }
            )
        })
    }
    
}