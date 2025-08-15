import { identity, Observable } from "rxjs";
import { AutocompleteDataResponse } from "../../model-acct/autocomplete-data-response";
import { AcctAutocompleteRepository } from "../autocomplete-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

/**
 * Implementation of the AcctAutocompleteRepository that connects to the back-end
 * services using the HTTP client abstraction layer
 */
export class HttpAcctAutocompleteRepository extends AcctAutocompleteRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    override findAutocompleteData(
        workspaceUUID           : string,
        accountUUID             : string,
        incomeOrExpenseItemUUID : string,
        textPattern             : string
    ): Observable<AutocompleteDataResponse[]> {
        return new Observable<AutocompleteDataResponse[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/autocomplete",
                    data: {
                        params: {
                            workspaceUUID           : workspaceUUID,
                            accountUUID             : accountUUID,
                            incomeOrExpenseItemUUID : incomeOrExpenseItemUUID,
                            textPattern             : textPattern
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "Autocomplete data not found."
                )
            )
        })
    }
    
}