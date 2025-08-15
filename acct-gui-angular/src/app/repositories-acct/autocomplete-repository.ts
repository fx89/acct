import { Observable } from "rxjs";
import { AutocompleteDataResponse } from "../model-acct/autocomplete-data-response";

/**
 * Allows reading autocomplete data for account records
 */
export abstract class AcctAutocompleteRepository {

    /**
     * Returns an observable that produces a collection of the first 10 account
     * record texts that match the given text pattern and the given income or
     * expense item recorded for the account for the given account UUID within
     * the workspace with the given workspace UUID. If the given text pattern
     * is shorter than 3 letters then an empty collection is returned.
     *
     * @param workspaceUUID           the given workspace UUID
     * @param accountUUID             the given account UUID
     * @param incomeOrExpenseItemUUID the given income or expense item
     * @param textPattern             the given text pattern
     */
    abstract findAutocompleteData(
        workspaceUUID           : string,
        accountUUID             : string,
        incomeOrExpenseItemUUID : string,
        textPattern             : string
    ) : Observable<AutocompleteDataResponse[]>

}