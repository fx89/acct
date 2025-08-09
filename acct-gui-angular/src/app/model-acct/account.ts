/**
 * Describes the account properties accepted by REST APIs
 */
export interface Account {

    /**
     * Unique identifier of the account
     */
    accountUUID? : string

    /**
     * Unique, human-readable, name of the account
     */
    accountName : string

    /**
     * UUID of the icon that should be displayed alongside the account
     */
    accountIconUUID : string

    /**
     * Bank account number that identifies the account at the bank
     */
    accountNumber : string

    /**
     * UUID of the account's currency
     */
    currencyUUID : string

    /**
     * UUID of the bank where the account was open
     */
    bankUUID : string

}

/**
 * Extends the Account interface to add the Base64-encoded content of the account's icon,
 * complete with its meta-data.
 */
export interface IconifiedAccount extends Account {

    /**
     * The Base64-encoded content of the accounts' icon, complete with its meta-data
     */
    imageData? : string

}