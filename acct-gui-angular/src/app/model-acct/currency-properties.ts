/**
 * Contains the readable properties of currencies
 */
export interface CurrencyProperties {

    /**
     * Unique identifier for the currency in the ACCT ecosystem
     */
    currencyUUID : string,

    /**
     * Unique 3-letter code that identifies the currency
     */
    currencyCode : string,

    /**
     * Human-readable name of the currency
     */
    currencyName : string,

    /**
     * UUID that identifies the currency in the GUI
     */
    currencyIconUUID : string

}

/**
 * Extends the CurrencyProperties with the imageData property, which contains the
 * Base64-encoded image and its meta-data, ready to be displayed by the browser
 */
export interface IconifiedCurrencyProperties extends CurrencyProperties {
    imageData : string
}