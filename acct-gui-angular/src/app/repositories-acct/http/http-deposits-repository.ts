import { map, Observable } from "rxjs";
import { DepositProperties } from "../../model-acct/deposit-modifiable-attributes";
import { DepositUUIDResponse } from "../../model-acct/deposit-uuid-response";
import { AcctDepositsRepository } from "../deposits-repository";
import { HttpConnector } from "../../services-reusable/http-connectors.service";
import { complete, newObservable } from "../../utils-reusalbe/rxjs-utils";
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
            return this.updateDepositAttributes().pipe(
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

    private updateDepositAttributes() : Observable<void> {
        throw new Error("Not yet implemented")
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
                        const page : AcctPage<DepositProperties> = {
                            page: (response.body as AcctPage<DepositDetails>).page,
                            data: ((response.body as AcctPage<DepositDetails>).data).map(rec => {
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

                        // Send the results up the pipe
                        complete(subscriber, page)
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }
    
}