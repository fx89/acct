/**
 * This is an object that wraps an account record ID so that it may be returned
 * as an object by a REST API
 */
export interface AccountRecordIdResponse {
    /**
     * the wrapped account record ID
     */
    accountRecordId : number
}