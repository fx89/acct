import { Observable } from "rxjs";
import { BankProperties } from "../model-acct/bank-properties";
import { BankUUIDResponse } from "../model-acct/bank-uuid-response";

/**
 * Allows creating, reading, updating and deleting banks
 */
export abstract class AcctBanksRepository {

    /**
     * Returns an observable that produces an array of all the banks registered within the catalog
     */
    abstract findAllBanks() : Observable<BankProperties[]>

    /**
     * Saves the bank with the given properties into the catalog. If the bank already exists, then
     * its properties are overwritten.
     * 
     * @param bankProperties the given properties
     */
    abstract saveBank(bankProperties:BankProperties) : Observable<BankUUIDResponse>

    /**
     * Deletes the banks having the UUIDs within the referenced array of bank UUIDs
     * 
     * @param bankUUIDs the referenced array of bank UUIDs
     */
    abstract deleteBanks(bankUUIDs:string[]) : Observable<void>

}