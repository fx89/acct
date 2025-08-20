import { Observable } from "rxjs";
import { DepositProperties } from "../../model-acct/deposit-modifiable-attributes";
import { DepositUUIDResponse } from "../../model-acct/deposit-uuid-response";
import { AcctDepositsRepository } from "../deposits-repository";

/**
 * Mock implementation of the AcctDepositsRepository
 */
export class MockAcctDepositsRepository extends AcctDepositsRepository {

    override saveDeposit(workspaceUUID: string, deposit: DepositProperties): Observable<DepositUUIDResponse> {
        throw new Error("Method not implemented.");
    }
    
}