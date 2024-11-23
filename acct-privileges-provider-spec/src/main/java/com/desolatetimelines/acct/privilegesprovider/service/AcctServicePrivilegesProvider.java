package com.desolatetimelines.acct.privilegesprovider.service;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

import java.util.Set;

/**
 * The privileges provider service defines the privileges that a given module expects
 * users to have to access functionality exposed by the module.
 */
public interface AcctServicePrivilegesProvider {

    /**
     * Returns a set containing the privileges supported by the module
     */
    Set<AcctPrivilege> getPrivileges();

}
