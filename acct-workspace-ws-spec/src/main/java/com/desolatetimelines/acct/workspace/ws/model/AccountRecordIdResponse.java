package com.desolatetimelines.acct.workspace.ws.model;

/**
 * This is an object that wraps an account record ID so that it may be returned
 * as an object by a REST API
 *
 * @param accountRecordId the wrapped account record ID
 */
public record AccountRecordIdResponse(
    Long accountRecordId
) {
}
