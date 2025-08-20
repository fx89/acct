import { Observable } from "rxjs";
import { DepositUUIDResponse } from "../model-acct/deposit-uuid-response";
import { DepositProperties } from "../model-acct/deposit-modifiable-attributes";
import { AcctPage } from "../model-acct/acct-page";

/**
 * Allows creating, reading, updating and deleting deposits
 */
export abstract class AcctDepositsRepository {

    /**
     * Saves the referenced deposit in the workspace with the given workspace UUID.
     * If the referenced deposit has a depositUUID defined, then the deposit having
     * the given depositUUID is updated. If the depositUUID is not defined, then a
     * new deposit is created with the properties of the referenced deposit.
     * 
     * @param workspaceUUID the given workspace UUID
     * @param deposit       the referenced deposit
     * 
     * @returns an observable that produces a container for the depositUUID of the
     *          saved deposit. This is useful when creating new deposits, since the
     *          depositUUID of a new deposit is not known until after it has been
     *          created.
     */
    abstract saveDeposit(workspaceUUID:string, deposit:DepositProperties) : Observable<DepositUUIDResponse>

    /**
     * Returns an observable that produces a page of deposits within the workspace
     * referenced by the given workspace UUID, sorted by the projected end date in
     * ascending order. Only the deposits at the bank with the given bank UUID are
     * fetched. 
     * 
     * @param workspaceUUID the given workspace UUID
     * @param bankUUID      the given bank UUID
     * @param pageNumber    the zero-based index of the page to be returned
     * @param pageSize      the number of elements to be contained by any given page
     */
    abstract findSortedPageOfDepositsByWorkspaceUUIDAndOptionalBankUUID(
        workspaceUUID : string,
        bankUUID      : string,
        pageNumber    : number,
        pageSize      : number
    ) : Observable<AcctPage<DepositProperties>>

}