import { identity, Observable } from "rxjs";
import { AcctBanksRepository } from "../banks-repository";
import { BankProperties } from "../../model-acct/bank-properties";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { BankUUIDResponse } from "../../model-acct/bank-uuid-response";

/**
 * Implementation of the banks repository that interacts with the Catalog back-end service
 */
export class HttpAcctBanksRepository extends AcctBanksRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findAllBanks(): Observable<BankProperties[]> {
        return new Observable<BankProperties[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/banks"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Banks not found."
                )
            )
        })
    }

    override saveBank(bankProperties: BankProperties): Observable<BankUUIDResponse> {
        return new Observable<BankUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If the bank has an UUID, then add it to the parameters object
            if (bankProperties.bankUUID) {
                params["bankUUID"] = bankProperties.bankUUID
            }

            this.httpConnector.put(
                {
                    url: "/banks",
                    data: {
                        params: params,
                        body: {
                            bankCode           : bankProperties.bankCode,
                            bankName           : bankProperties.bankName,
                            internetBankingURL : bankProperties.internetBankingURL,
                            bankIconUUID       : bankProperties.bankIconUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:BankUUIDResponse) => responseBody,
                    "Bank not created."
                )
            )
        })
    }

    override deleteBanks(bankUUIDs: string[]): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/banks",
                    data: {
                        params: {
                            bankUUIDs: bankUUIDs
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
    
}