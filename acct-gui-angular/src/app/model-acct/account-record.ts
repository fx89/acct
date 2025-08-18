/**
 * Container for all the properties that can be persisted for an account record
 */
export interface AccountRecordInputData {
    /**
     * Unique identifier of the account record within the account
     */
    accountRecordId? : number,

    /**
     * The UUID of the income or expense item that the account record relates to
     */
    incomeOrExpenseItemUUID : string,

    /**
     * The human-readable description of the income or expense
     */
    accountRecordText : string,

    /**
     * The income or expense value
     */
    accountRecordValue : number,

    /**
     * The date when the transaction took place
     */
    accountRecordDate? : Date
}

/**
 * Container for all the properties that can be returned by a REST API for a given account record
 */
export interface AccountRecord extends AccountRecordInputData {

    /**
     * The date at which the record was created
     */
    accountRecordDate : Date,

    /**
     * The UUID of the user who crated the record
     */
    recordedByUserUUID : string,

    /**
     * The date at which the record was last updated
     */
    lastModifiedDate : Date,

    /**
     * The UUID of the user who made the latest update to the record
     */
    lastModifiedByUserUUID : string,

    /**
     * Optional exchange rate applied when buying the foreign currency, available only if
     * the record describes a foreign currency purchase
     */
    exchangeRate? : number,

    /**
     * Optional purchase price of foreign currencies, calculated as
     * accountRecordValue * exchangeRate
     */
    purchasePrice? : number,

    /**
     * Optional exchange rate applied when selling the foreign currency, available only if
     * the record describes a foreign currency sale
     */
    sellRate? : number

    /**
     * Optional exchange rate applied for buying back the currency, available only if the
     * record describes a foreign currency exchange which has been bought back
     */
    buyBackRate? : number

}