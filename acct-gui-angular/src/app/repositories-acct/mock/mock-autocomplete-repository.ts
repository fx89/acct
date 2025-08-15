import { Observable } from "rxjs";
import { AutocompleteDataResponse } from "../../model-acct/autocomplete-data-response";
import { AcctAutocompleteRepository } from "../autocomplete-repository";

/**
 * Mock implementation of the AcctAutocompleteRepository
 */
export class MockAcctAutocompleteRepository extends AcctAutocompleteRepository {

    override findAutocompleteData(workspaceUUID: string, accountUUID: string, incomeOrExpenseItemUUID: string, textPattern: string): Observable<AutocompleteDataResponse[]> {
        throw new Error("Method not implemented.");
    }
    
}