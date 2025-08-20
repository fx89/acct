/**
 * This is an object that wraps a deposit UUID so that it may be returned
 * as an object by a REST API
 */
export interface DepositUUIDResponse {
    /**
     * The wrapped deposit UUID
     */
    depositUUID : string
}