package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Container for autocomplete data for suggesting previously-used values for recurring
 * income or expenses
 *
 * @param accountRecordText          account record text that was previously used
 * @param lastUsedAccountRecordValue the last value recorded for the account record text
 */
public record AutocompleteDataResponse(
    String accountRecordText,
    Double lastUsedAccountRecordValue
) {
    public static AutocompleteDataResponseBuilder builder() {
        return new AutocompleteDataResponseBuilder();
    }

    public static final class AutocompleteDataResponseBuilder {
        private String accountRecordText;
        private Double lastUsedAccountRecordValue;

        private AutocompleteDataResponseBuilder() {
        }

        public AutocompleteDataResponseBuilder withAccountRecordText(String accountRecordText) {
            this.accountRecordText = accountRecordText;
            return this;
        }

        public AutocompleteDataResponseBuilder withLastUsedAccountRecordValue(Double lastUsedAccountRecordValue) {
            this.lastUsedAccountRecordValue = lastUsedAccountRecordValue;
            return this;
        }

        public AutocompleteDataResponse build() {
            return new AutocompleteDataResponse(accountRecordText, lastUsedAccountRecordValue);
        }
    }
}
