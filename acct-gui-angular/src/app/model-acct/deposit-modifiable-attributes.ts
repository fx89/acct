/**
 * Contains deposit properties that can be modified after the deposit has been created
 */
export interface DepositModifiableAttributes {
    /**
     * The account number of the deposit
     */
    depositAccountNumber : string

    /**
     * The date when the deposit was created
     */
    startDate : Date

    /**
     * The date when the deposit is expected to capitalize
     */
    projectedEndDate : Date

    /**
     * Optional UUID that uniquely identifies the deposit within the ACCT ecosystem.
     * To be provided when the intention is to update an existing deposit. To be left
     * undefined when the intention is to register a new deposit.
     */
    depositUUID? : string

    /**
     * The UUID of the currency in which the deposit was created
     */
    currencyUUID? : string
}

/**
 * Contains the basic properties that can be transferred from the front-end to the
 * back-end with a deposit creation request.
 */
export interface DepositProperties extends DepositModifiableAttributes {
    /**
     * The UUID of the account from where the money is transferred to the new deposit
     */
    sourceAccountUUID : string

    /**
     * The amount of money taken from the source account and stored into the deposit account
     */
    amount : number

    /**
     * The interest percentage of the deposit
     */
    interestPct : number
}