package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Describes the account properties accepted by REST APIs
 */
public record AccountProperties(
    String accountName,
    String accountIconUUID,
    String accountNumber,
    String currencyUUID,
    String bankUUID
) {

}
