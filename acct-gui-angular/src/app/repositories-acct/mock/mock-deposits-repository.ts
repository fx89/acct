import { Observable } from "rxjs";
import { DepositProperties } from "../../model-acct/deposit-modifiable-attributes";
import { DepositUUIDResponse } from "../../model-acct/deposit-uuid-response";
import { AcctDepositsRepository } from "../deposits-repository";
import { AcctPage } from "../../model-acct/acct-page";

/**
 * Mock implementation of the AcctDepositsRepository
 */
export class MockAcctDepositsRepository extends AcctDepositsRepository {

    override saveDeposit(workspaceUUID: string, deposit: DepositProperties): Observable<DepositUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    override capitalizeDeposit(
        workspaceUUID: string,
        deposit: DepositProperties,
        depositReturnValue: number
    ): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override findSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
        workspaceUUID : string,
        bankUUID      : string,
        pageNumber    : number,
        pageSize      : number
    ) : Observable<AcctPage<DepositProperties>> {
        throw new Error("Method not implemented.");
    }

    override findSortedPageOfDepositsToCapitalizeByWorkspaceUUIDAndBankUUID(
        workspaceUUID : string,
        bankUUID      : string,
        pageNumber    : number,
        pageSize      : number
    ): Observable<AcctPage<DepositProperties>> {
        throw new Error("Method not implemented.");
    }

}