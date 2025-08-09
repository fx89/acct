import { Observable } from "rxjs";
import { Account } from "../../model-acct/account";
import { AccountBalanceResponse } from "../../model-acct/account-balance-response";
import { AccountUUIDResponse } from "../../model-acct/account-uuid-response";
import { AcctAccountsRepository } from "../accounts-repository";

/**
 * Mock implementation of the AcctAccountsRepository
 */
export class MockAcctAccountsRepository extends AcctAccountsRepository {

    override findAccountsByWorkspaceUUID(workspaceUUID: string): Observable<Account[]> {
        throw new Error("Method not implemented.");
    }

    override saveAccount(workspaceUUID: string, account: Account): Observable<AccountUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    override deleteAccount(workspaceUUID: string, accountUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    override findAccountBalance(workspaceUUID: string, accountUUID: string): Observable<AccountBalanceResponse> {
        throw new Error("Method not implemented.");
    }

}