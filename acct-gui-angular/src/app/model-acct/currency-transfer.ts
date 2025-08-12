import { Account } from "./account";

/**
 * Describes a transfer of a currency amount between two accounts having the same currency
 */
export interface CurrencyTransfer {

    /**
     * The account from which the amount is transferred
     */
    sourceAccount : Account,

    /**
     * The account to which the amount ins transferred
     */
    targetAccount : Account,

    /**
     * The amount that is transferred
     */
    amount : number

}