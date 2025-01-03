package com.desolatetimelines.acct.workspace.ws.model;

/**
 * Defines account record properties that are transferable from the front-end to the back-end
 *
 * @param incomeOrExpenseItemUUID The UUID of the income or expense item that the account record relates to
 * @param accountRecordText       The human-readable description of the income or expense
 * @param accountRecordValue      The income or expense value
 */
public record AccountRecordProperties(
    String incomeOrExpenseItemUUID,
    String accountRecordText,
    Double accountRecordValue
) {
}
