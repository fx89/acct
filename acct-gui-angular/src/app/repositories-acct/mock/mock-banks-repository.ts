import { Observable } from "rxjs";
import { BankProperties } from "../../model-acct/bank-properties";
import { AcctBanksRepository } from "../banks-repository";
import { BankUUIDResponse } from "../../model-acct/bank-uuid-response";

/**
 * Mock implementation of the banks repository
 */
export class MockAcctBanksRepository extends AcctBanksRepository {

    override findAllBanks(): Observable<BankProperties[]> {
        throw new Error("Method not implemented.");
    }

    override saveBank(bankProperties: BankProperties): Observable<BankUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    override deleteBanks(bankUUIDs: string[]): Observable<void> {
        throw new Error("Method not implemented.");
    }
    
}