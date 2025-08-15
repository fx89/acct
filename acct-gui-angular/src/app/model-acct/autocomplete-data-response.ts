/**
 * Container for autocomplete data for suggesting previously-used values for recurring
 * income or expenses
 */
export interface AutocompleteDataResponse {
    /**
     * Account record text that was previously used
     */
    accountRecordText : string,

    /**
     * The last value recorded for the account record text
     */
    lastUsedAccountRecordValue : number
}