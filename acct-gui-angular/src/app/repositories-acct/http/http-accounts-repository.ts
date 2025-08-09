import { identity, Observable } from "rxjs";
import { Account } from "../../model-acct/account";
import { AccountBalanceResponse } from "../../model-acct/account-balance-response";
import { AccountUUIDResponse } from "../../model-acct/account-uuid-response";
import { AcctAccountsRepository } from "../accounts-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

/**
 * Implementation of the AcctAccountsRepository that uses the HTTPClient abstraction layer
 * to communicate with the back-end services
 */
export class HttpAcctAccountsRepository extends AcctAccountsRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findAccountsByWorkspaceUUID(workspaceUUID: string): Observable<Account[]> {
        return new Observable<Account[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/accounts",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Accounts not found."
                )
            )
        })
    }

    override saveAccount(workspaceUUID: string, account: Account): Observable<AccountUUIDResponse> {
        return new Observable<AccountUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // Add the workspace UUID parameter
            params["workspaceUUID"] = workspaceUUID

            // If the account has an UUID, then add it to the parameters object
            if (account.accountUUID) {
                params["accountUUID"] = account.accountUUID
            }

            this.httpConnector.put(
                {
                    url: "/accounts",
                    data: {
                        params: params,
                        body: {
                            accountName     : account.accountName,
                            accountIconUUID : account.accountIconUUID,
                            accountNumber   : account.accountNumber,
                            currencyUUID    : account.currencyUUID,
                            bankUUID        : account.bankUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:AccountUUIDResponse) => responseBody,
                    "Account not created."
                )
            )
        })
    }

    override deleteAccount(workspaceUUID: string, accountUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/accounts",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            accountUUID   : accountUUID
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

    override findAccountBalance(workspaceUUID: string, accountUUID: string): Observable<AccountBalanceResponse> {
        return new Observable(subscriber => {
            this.httpConnector.get(
                {
                    url: "/accounts/balance",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            accountUUID   : accountUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:AccountBalanceResponse) => responseBody,
                    "Account balance not found."
                )
            )
        })
    }
    
}