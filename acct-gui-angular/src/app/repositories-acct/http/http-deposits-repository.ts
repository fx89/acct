import { map, Observable } from "rxjs";
import { DepositProperties } from "../../model-acct/deposit-modifiable-attributes";
import { DepositUUIDResponse } from "../../model-acct/deposit-uuid-response";
import { AcctDepositsRepository } from "../deposits-repository";
import { HttpConnector } from "../../services-reusable/http-connectors.service";
import { complete } from "../../utils-reusalbe/rxjs-utils";
import { AcctPage } from "../../model-acct/acct-page";

/**
 * Type returned from the back-end, mapped to the more generic front-end type
 */
type DepositDetails = {
    depositUUID             : string,
    sourceAccountUUID       : string,
    depositAccountNumber    : string,
    currencyUUID            : string,
    bankUUID                : string,
    depositValue            : number,
    depositInterestPercent  : number,
    depositStartDate        : string,
    depositProjectedEndDate : string
}

/**
 * Implementation of the AcctDepositsRepository that connects to the back-end
 * services to provide functionality
 */
export class HttpAcctDepositsRepository extends AcctDepositsRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    override saveDeposit(workspaceUUID: string, deposit: DepositProperties): Observable<DepositUUIDResponse> {
        // If the deposit UUID was provided, then update the deposit
        if (deposit.depositUUID) {
            return this.updateDepositAttributes(workspaceUUID, deposit).pipe(
                map(() => { 
                    return { depositUUID: deposit.depositUUID as string } 
                })
            )
        }
        // If the deposit UUID was not provided, then create the deposit
        else {
            return this.createDepositFromSourceAccount(workspaceUUID, deposit)
        }
    }

    private createDepositFromSourceAccount(
        workspaceUUID: string,
        deposit: DepositProperties
    ) : Observable<DepositUUIDResponse> {
        return new Observable<DepositUUIDResponse>(subscriber => {
            this.httpConnector.put(
                {
                    url: "/deposits",
                    data: {
                        params: {
                            workspaceUUID: workspaceUUID
                        },
                        body: {
                            sourceAccountUUID    : deposit.sourceAccountUUID,
                            depositAccountNumber : deposit.depositAccountNumber,
                            amount               : deposit.amount,
                            startDate            : deposit.startDate,
                            projectedEndDate     : deposit.projectedEndDate,
                            interestPct          : deposit.interestPct
                        }
                    }
                },
                {
                    responseHandler: response => complete(subscriber, response.body),
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    private updateDepositAttributes(workspaceUUID: string, deposit: DepositProperties) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/deposits",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            depositUUID   : deposit.depositUUID ?? ""
                        },
                        body: {
                            depositAccountNumber : deposit.depositAccountNumber,
                            projectedEndDate     : deposit.projectedEndDate
                        }
                    }
                },
                {
                    responseHandler: () => {
                        complete(subscriber, undefined)
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override capitalizeDeposit(
        workspaceUUID: string,
        deposit: DepositProperties,
        depositReturnValue: number
    ): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/deposits/capitalize",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            depositUUID   : deposit.depositUUID ?? ""
                        },
                        body: {
                            returnValue : depositReturnValue
                        }
                    }
                },
                {
                    responseHandler: () => {
                        complete(subscriber, undefined)
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override findSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
        workspaceUUID : string,
        bankUUID      : string,
        pageNumber    : number,
        pageSize      : number
    ) : Observable<AcctPage<DepositProperties>> {
        return new Observable<AcctPage<DepositProperties>>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/deposits",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            bankUUID      : bankUUID,
                            pageNumber    : pageNumber,
                            pageSize      : pageSize
                        }
                    }
                },
                {
                    responseHandler: response => {
                        // Transform the results
                        const page : AcctPage<DepositProperties> =
                            this.pageOfDepositDetailsToPageOfDepositPropertiesMapper(
                                response.body as AcctPage<DepositDetails>
                            )

                        // Send the results up the pipe
                        complete(subscriber, page)
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    override findSortedPageOfDepositsToCapitalizeByWorkspaceUUIDAndBankUUID(
        workspaceUUID : string,
        bankUUID      : string,
        pageNumber    : number,
        pageSize      : number
    ): Observable<AcctPage<DepositProperties>> {
        return new Observable<AcctPage<DepositProperties>>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/deposits/toCapitalize",
                    data: {
                        params: {
                            workspaceUUID : workspaceUUID,
                            bankUUID      : bankUUID,
                            pageNumber    : pageNumber,
                            pageSize      : pageSize
                        }
                    }
                },
                {
                    responseHandler: response => {
                        // Transform the results
                        const page : AcctPage<DepositProperties> =
                            this.pageOfDepositDetailsToPageOfDepositPropertiesMapper(
                                response.body as AcctPage<DepositDetails>
                            )

                        // Send the results up the pipe
                        complete(subscriber, page)
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

    private pageOfDepositDetailsToPageOfDepositPropertiesMapper(source:AcctPage<DepositDetails>) : AcctPage<DepositProperties> {
        return {
            page: source.page,
            data: source.data.map(rec => {
                return {
                    depositUUID          : rec.depositUUID,
                    depositAccountNumber : rec.depositAccountNumber,
                    sourceAccountUUID    : rec.sourceAccountUUID,
                    amount               : rec.depositValue,
                    interestPct          : rec.depositInterestPercent,
                    startDate            : new Date(rec.depositStartDate),
                    projectedEndDate     : new Date(rec.depositProjectedEndDate),
                    currencyUUID         : rec.currencyUUID
                }
            })
        }
    }
    
}