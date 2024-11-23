package com.desolatetimelines.acct.authorization.data.service;

import com.desolatetimelines.acct.authorization.data.model.AcctUser;

import java.util.Optional;

/**
 * Specification for the ACCT authorization server data service
 */
public interface AcctAuthorizationServerDataService {

    /**
     * Returns the {@link AcctUser details} of the user with the given {@code loginName}
     * or an empty optional if no such user exists.
     */
    Optional<AcctUser> getUserByLoginName(String loginName);

}
