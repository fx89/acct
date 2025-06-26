package com.desolatetimelines.acct.authorization.model;

/**
 * A container for a user access token, that can be returned from a REST controller
 */
public record UserAccessTokenResponse(
    String userAccessToken
) {
}
