package com.desolatetimelines.acct.security.ws.mapper;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;

/**
 * Provides mapper methods for the {@link AcctPrivilege} object
 */
public abstract class AcctPrivilegeMapper {

    /**
     * Maps the given {@link AcctPrivilege data layer privilege} to
     * a new {@link Privilege presentation layer privilege}
     *
     * @param acctPrivilege the given data layer privilege
     */
    public static Privilege toPrivilege(AcctPrivilege acctPrivilege) {
        return
            Privilege.builder()
                .withPrivilegeId(acctPrivilege.privilegeId())
                .withPrivilegeName(acctPrivilege.privilegeName())
                .withPrivilegeDescription(acctPrivilege.privilegeDescription())
                .build();
    }

}
