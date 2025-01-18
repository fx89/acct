package com.desolatetimelines.acct.workspace.ws.endpoint;

import com.desolatetimelines.acct.workspace.ws.model.AutocompleteDataResponse;

import java.util.Collection;

/**
 * Specifies autocomplete endpoint functionality that can be accessed by both
 * clients and other ACCT services. Defines both client and server functionality.
 */
public interface AutocompleteEndpoint {

    /**
     * Returns a collection of the first 10 account record texts that match the
     * given text pattern and the given income or expense item recorded for the
     * account for the given account UUID within the workspace with the given
     * workspace UUID. If the given text pattern is shorter than 3 letters then
     * an empty collection is returned.
     *
     * @param workspaceUUID           the given workspace UUID
     * @param accountUUID             the given account UUID
     * @param incomeOrExpenseItemUUID the given income or expense item
     * @param textPattern             the given text pattern
     */
    Collection<AutocompleteDataResponse> getAutocompleteData(
        String workspaceUUID,
        String accountUUID,
        String incomeOrExpenseItemUUID,
        String textPattern
    );

}
