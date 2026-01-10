/**
 * Describes the readable properties of a data provider instance in the ACCT ecosystem
 */
export interface DataProviderInstance {
    /**
     * The unique identifier of the data provider instance in the ACCT ecosystem
     */
    dataProviderInstanceUUID? : string

    /**
     * Human-readable name for the data provider instance
     */
    dataProviderInstanceName : string

    /**
     * Unique identifier of the data provider whose instance this is
     */
    dataProviderUUID : string
}