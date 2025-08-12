import { CurrencyTransfer } from "./currency-transfer";

/**
 * Describes a currency exchange request, which contains a currency transfer request
 * and an exchange rate. If this currency transfer is supposed to be a buy-back related
 * to a previously recorded exchange (i.e. previously bought EUR with USD and now the
 * EUR is sold for USD to make a profit in USD), then an original account record ID may
 * be provided.
 * 
 */
export interface CurrencyExchange {

    /**
     * Embedded currency transfer request, for providing references to the source and
     * target account, as well as the amount to be transfered.
     */
    currencyTransfer : CurrencyTransfer,

    /**
     * The exchange rate at which the currency of the target account is purchased against
     * the currency of the source account.
     */
    exchangeRate : number,

    /**
     * Optional id of the account record that describes the original exchange of currency
     * for which this currency exchange is a buy-back.
     */
    originalAccountRecordId? : number

}