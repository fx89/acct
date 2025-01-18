package com.desolatetimelines.acct.workspace.ws.mapper;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecordAutocompleteData;
import com.desolatetimelines.acct.workspace.ws.model.AutocompleteDataResponse;

import java.util.Collection;

/**
 * Provides mapper methods for the {@link AutocompleteDataResponse} type
 */
public abstract class AutocompleteDataResponseMapper {

    public static AutocompleteDataResponse fromAcctAccountRecordAutocompleteData(AcctAccountRecordAutocompleteData data) {
        return
            AutocompleteDataResponse.builder()
                .withAccountRecordText(data.getAccountRecordText())
                .withLastUsedAccountRecordValue(data.getLastUsedAccountRecordValue())
                .build();
    }

    public static Collection<AutocompleteDataResponse> fromCollectionOfAcctAccountRecordAutocompleteData(
        Collection<AcctAccountRecordAutocompleteData> dataCollection
    ) {
        return
            dataCollection.stream()
                .map(AutocompleteDataResponseMapper::fromAcctAccountRecordAutocompleteData)
                .toList();
    }

}
