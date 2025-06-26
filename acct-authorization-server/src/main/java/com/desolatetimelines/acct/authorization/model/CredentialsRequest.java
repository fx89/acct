package com.desolatetimelines.acct.authorization.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * REST API request object that contains the credentials of a given user and
 * the client ID of the client that provides the user credentials
 *
 * @param clientId     the client ID of the client that provides the user credentials
 * @param clientSecret the client secret of the client that provides the user credentials
 * @param username     the given user's username
 * @param password     the given user's password
 */
@Validated
public record CredentialsRequest(
    @NotNull @NotEmpty String clientId,
    @NotNull @NotEmpty String clientSecret,
    @NotNull @NotEmpty String username,
    @NotNull @NotEmpty String password
) {
}
