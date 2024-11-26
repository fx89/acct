package com.desolatetimelines.acct.security.ws.endpoint;

import com.desolatetimelines.acct.security.ws.endpoint.model.GroupPrivileges;
import com.desolatetimelines.acct.security.ws.endpoint.model.Privilege;

import java.util.Collection;
import java.util.Set;

/**
 * Specification for the privileges' endpoint. Defines both client and server functionality.
 */
public interface PrivilegesEndpoint {

    /**
     * Returns a set of privileges mapped to the groups having the UUIDs in the given list
     */
    Set<String> getGroupPrivilegesByGroupUUIDs(Collection<String> groupUUIDs);

    /**
     * Returns a set of all the privileges that can be assigned to a group
     */
    Set<Privilege> getAllAssignablePrivileges();

    /**
     * Assigns a set of privileges to a group
     */
    void assignPrivilegesToGroup(GroupPrivileges groupPrivileges);

}
