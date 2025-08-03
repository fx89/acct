import { identity, Observable } from "rxjs";
import { CurrencyProperties } from "../../model-acct/currency-properties";
import { CurrencyUUIDResponse } from "../../model-acct/currency-uuid-response";
import { AcctCurrenciesRepository } from "../currencies-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

/**
 * Implementation of the AcctCurrenciesRepository that uses the HTTP client abstraction layer
 * to communicate with the Catalog back-end service
 */
export class HttpAcctCurrenciesRepository extends AcctCurrenciesRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findCurrencies(): Observable<CurrencyProperties[]> {
        return new Observable<CurrencyProperties[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/currencies"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Currencies not found."
                )
            )
        })
    }

    override deleteCurrencies(currencyUUIDs: string[]): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/currencies",
                    data: {
                        params: {
                            currencyUUIDs: currencyUUIDs
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override saveCurrency(curreny: CurrencyProperties): Observable<CurrencyUUIDResponse> {
        return new Observable<CurrencyUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If the bank has an UUID, then add it to the parameters object
            if (curreny.currencyUUID) {
                params["currencyUUID"] = curreny.currencyUUID
            }

            this.httpConnector.put(
                {
                    url: "/currencies",
                    data: {
                        params: params,
                        body: {
                            currencyCode     : curreny.currencyCode,
                            currencyName     : curreny.currencyName,
                            currencyIconUUID : curreny.currencyIconUUID,
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:CurrencyUUIDResponse) => responseBody,
                    "Currency not created."
                )
            )
        })
    }

}