import { Observable } from "rxjs";
import { Account } from "../model-acct/account";
import { AccountUUIDResponse } from "../model-acct/account-uuid-response";
import { AccountBalanceResponse } from "../model-acct/account-balance-response";

/**
 * Allows creating, reading, updating and deleting accounts
 */
export abstract class AcctAccountsRepository {

    /**
     * Returns an observable that produces an array of the accounts that are registered
     * within the scope of the workspace having the given workspaceUUID
     * 
     * @param workspaceUUID the given workspaceUUID
     */
    abstract findAccountsByWorkspaceUUID(workspaceUUID:string) : Observable<Account[]>

    /**
     * Saves the referenced account within the workspace referenced by the given workspaceUUID.
     * If the account is new, then it is registered. If it already exists, then it is updated.
     * 
     * @param workspaceUUID the given workspaceUUID
     * @param account       the referenced account
     * @returns an observable that produces an AccountUUIDResponse which contains the UUID of
     *          the saved account.
     */
    abstract saveAccount(workspaceUUID:string, account:Account) : Observable<AccountUUIDResponse>

    /**
     * Deletes the account with the given accountUUID from the workspace having the given
     * workspaceUUID
     * 
     * @param workspaceUUID the given workspaceUUID
     * @param accountUUID   the given accountUUID
     */
    abstract deleteAccount(workspaceUUID:string, accountUUID:string) : Observable<void>

    /**
     * Returns an observable that produces an AccountBalanceResponse which contains the balance
     * of the account with the given accountUUID from the workspace with the given workspaceUUID.
     * 
     * @param workspaceUUID the given workspaceUUID
     * @param accountUUID   the given accountUUID
     */
    abstract findAccountBalance(workspaceUUID:string, accountUUID:string) : Observable<AccountBalanceResponse>

}