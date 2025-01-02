package com.desolatetimelines.acct.workspace.ws.model;

/**
 * This is an object that wraps an account UUID so that it may be returned
 * as an object by a REST API
 *
 * @param accountUUID the wrapped account UUID
 */
public record AccountUUIDResponse(
    String accountUUID
) {
}
