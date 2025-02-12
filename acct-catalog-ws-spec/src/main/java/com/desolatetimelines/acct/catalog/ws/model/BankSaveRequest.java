package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Defines the modifiable properties of a bank
 *
 * @param bankCode           The unique code given to the bank in the ACCT ecosystem (i.e. ING, BNR, BT, BCR, etc.)
 * @param bankName           The unique human-readable name of the bank
 * @param internetBankingURL The optional internet banking URL for the bank
 * @param bankIconUUID       The UUID of the optional icon that represents the bank on the ACCT GUI
 */
public record BankSaveRequest(
    String bankCode,
    String bankName,
    String internetBankingURL,
    String bankIconUUID
) {
}
