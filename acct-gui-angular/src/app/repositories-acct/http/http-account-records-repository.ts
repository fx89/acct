import { identity, Observable } from "rxjs";
import { AccountRecordInputData, AccountRecord } from "../../model-acct/account-record";
import { AccountRecordIdResponse } from "../../model-acct/account-record-id-response";
import { AcctPage } from "../../model-acct/acct-page";
import { AcctAccountRecordsRepository } from "../account-records-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { complete } from "../../utils-reusalbe/rxjs-utils";
import { SortDirection } from "../../model-acct/sort-direction";

/**
 * Implementation of the AcctAccountRecordsRepository that uses the HTTPClient abstraction layer to
 * communicate with the back-end services
 */
export class HttpAcctAccountRecordsRepository extends AcctAccountRecordsRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override saveAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ): Observable<AccountRecordIdResponse>
    {
        return new Observable<AccountRecordIdResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // Add the workspace UUID parameter
            params["workspaceUUID"] = workspaceUUID

            // Add the account UUID parameter
            params["accountUUID"] = accountUUID

            // If the account record has an id, then add it to the parameters object
            if (record.accountRecordId) {
                params["accountRecordId"] = record.accountRecordId
            }

            this.httpConnector.put(
                {
                    url: "/accountRecords",
                    data: {
                        params: params,
                        body: {
                            incomeOrExpenseItemUUID : record.incomeOrExpenseItemUUID,
                            accountRecordText       : record.accountRecordText,
                            accountRecordValue      : record.accountRecordValue
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Account record not saved."
                )
            )
        })
    }

    override deleteAccountRecord(
        workspaceUUID : string,
        accountUUID   : string,
        record        : AccountRecordInputData
    ): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/accountRecords",
                    data: {
                        params: {
                            workspaceUUID   : workspaceUUID,
                            accountUUID     : accountUUID,
                            accountRecordId : record.accountRecordId ?? ""
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

    override findSortedPageOfAccountRecordsByTextPattern(
        workspaceUUID : string,
        accountUUID   : string,
        pageNumber    : number,
        pageSize      : number,
        sortDirection : SortDirection,
        pattern?      : string
    ): Observable<AcctPage<AccountRecord>>
    {
        // Create parameters object
        const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

        // Add the workspace UUID parameter
        params["workspaceUUID"] = workspaceUUID

        // Add the account UUID parameter
        params["accountUUID"] = accountUUID

        // Add the page number parameter
        params["pageNumber"] = pageNumber

        // Add the page size parameter
        params["pageSize"] = pageSize

        // Add the sort direction
        params["sortDirection"] = (sortDirection == SortDirection.ASCENDING ? "ASCENDING" : "DESCENDING")

        // If the pattern is specified, then add it to the parameters object
        if (pattern) {
            params["pattern"] = pattern
        }

        return new Observable<AcctPage<AccountRecord>>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/accountRecords",
                    data: {
                        params: params
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Account records not found."
                )
            )
        })
    }

    override saveCurrencyTransfer(
        workspaceUUID     : string,
        sourceAccountUUID : string,
        targetAccountUUID : string,
        amount            : number
    ): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/accountRecords/transfer",
                    data: {
                        params: {
                            workspaceUUID: workspaceUUID
                        },
                        body: {
                            sourceAccountUUID: sourceAccountUUID,
                            targetAccountUUID: targetAccountUUID,
                            amount: amount
                        }
                    }
                },
                {
                    responseHandler: () => complete(subscriber, undefined),
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override saveCurrencyExchange(
        workspaceUUID            : string,
        sourceAccountUUID        : string,
        targetAccountUUID        : string,
        amount                   : number,
        exchangeRate             : number,
        originalAccountRecordId? : number
    ): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/accountRecords/exchange",
                    data: {
                        params: {
                            workspaceUUID: workspaceUUID
                        },
                        body: {
                            currencyTransfer: {
                                sourceAccountUUID: sourceAccountUUID,
                                targetAccountUUID: targetAccountUUID,
                                amount: amount
                            },
                            exchangeRate: exchangeRate,
                            originalAccountRecordId: originalAccountRecordId ?? null
                        }
                    }
                },
                {
                    responseHandler: () => complete(subscriber, undefined),
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }
    
}