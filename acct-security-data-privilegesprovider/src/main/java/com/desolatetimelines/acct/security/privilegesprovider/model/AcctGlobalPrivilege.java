package com.desolatetimelines.acct.security.privilegesprovider.model;

import com.desolatetimelines.acct.privilegesprovider.model.AcctPrivilege;

public enum AcctGlobalPrivilege {

    ADMIN_OPERATIONS(
        AcctPrivilege.builder()
            .withPrivilegeId(AcctGlobalPrivilegeIds.ADMIN_OPERATIONS)
            .withPrivilegeName("Administrative operations")
            .withPrivilegeDescription("Allows users to invoke functionality that's normally reserved for interoperability between back-end modules")
            .build()
    );

    private final AcctPrivilege acctPrivilege;

    AcctGlobalPrivilege(AcctPrivilege acctPrivilege) {
        this.acctPrivilege = acctPrivilege;
    }

    public AcctPrivilege getAcctPrivilege() {
        return acctPrivilege;
    }

}
