import { Observable } from "rxjs";
import { DepositUUIDResponse } from "../model-acct/deposit-uuid-response";
import { DepositProperties } from "../model-acct/deposit-modifiable-attributes";

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


}