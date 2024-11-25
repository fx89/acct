package com.desolatetimelines.acct.usermanagement.model;

/**
 * Defines the mapping between a user and a group
 */
public interface AcctUserGroupMapping {

    AcctUser getUser();

    void setUser(AcctUser acctUser);

    AcctUsersGroup getGroup();

    void setGroup(AcctUsersGroup acctUsersGroup);

}
