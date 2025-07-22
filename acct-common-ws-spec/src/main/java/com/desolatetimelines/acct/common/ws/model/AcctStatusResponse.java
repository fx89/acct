package com.desolatetimelines.acct.common.ws.model;

/**
 * Generic status response for endpoints that have nothing else to return
 *
 * @param status the status description
 */
public record AcctStatusResponse(
    String status
) {
    public static AcctStatusResponse newAcctOkResponse() {
        return new AcctStatusResponse("ok");
    }

    public static AcctStatusResponse newAcctFailedResponse() {
        return new AcctStatusResponse("failed");
    }
}
