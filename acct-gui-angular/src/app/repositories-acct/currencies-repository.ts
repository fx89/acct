import { Observable } from "rxjs";
import { CurrencyProperties } from "../model-acct/currency-properties";
import { CurrencyUUIDResponse } from "../model-acct/currency-uuid-response";

/**
 * Allows creating, reading, updating and deleting currencies
 */
export abstract class AcctCurrenciesRepository {

    /**
     * Returns an observable that produces an array of all the currencies registered within the catalog
     */
    abstract findCurrencies() : Observable<CurrencyProperties[]>

    /**
     * Deletes the currencies identified by the UUIDs in the given array from the catalog
     * 
     * @param currencyUUIDs the given array
     */
    abstract deleteCurrencies(currencyUUIDs:string[]) : Observable<void>

    /**
     * Saves the referenced currency into the catalog and returns an observable that produces the UUID
     * of the saved currency
     * 
     * @param curreny the referenced currency
     */
    abstract saveCurrency(curreny:CurrencyProperties) : Observable<CurrencyUUIDResponse>

}