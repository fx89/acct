package com.desolatetimelines.acct.privilegesprovider.model;

public enum AcctGlobalPrivilege {

    ADMIN_OPERATIONS(
        AcctPrivilege.builder()
            .withPrivilegeId("ADMIN_OPERATIONS")
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
