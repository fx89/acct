/**
 * Container for the readable properties of banks
 */
export interface BankProperties {

    /**
     * Unique identifier for the bank in the ACCT ecosystem
     */
    bankUUID? : string,

    /**
     * The unique code given to the bank in the ACCT ecosystem (i.e. ING, BNR, BT, BCR, etc.)
     */
    bankCode : string,

    /**
     * The unique human-readable name of the bank
     */
    bankName : string,

    /**
     * The optional internet banking URL for the bank
     */
    internetBankingURL : string,

    /**
     * The UUID of the optional icon that represents the bank on the ACCT GUI
     */
    bankIconUUID : string

}

/**
 * Extends the BankProperties with the imageData property, which contains the
 * Base64-encoded image and its meta-data, ready to be displayed by the browser
 */
export interface IconifiedBankProperties extends BankProperties {
    imageData : string
}