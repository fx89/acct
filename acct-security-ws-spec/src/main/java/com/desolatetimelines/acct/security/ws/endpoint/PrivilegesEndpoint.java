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
    void assignPrivilegesToGroup(GroupPrivileges groupPrivilegeIDs);

    /**
     * Removes the privileges referenced by the given collection from the group identified
     * by the given group UUID. If a privilege is not assigned to the group, it is ignored.
     */
    void removePrivilegesFromGroup(String groupUUID, Collection<String> privilegeIDs);

    /**
     * Returns a list of privilege IDs for all the privileges assigned to all the groups
     * mapped to the user with the given user UUID
     *
     * @param userUUID the given user UUID
     */
    Collection<String> getPrivilegesAssignedToUser(String userUUID);

    /**
     * Returns a list of privilege IDs for all the privileges assigned to all the groups
     * mapped to the current user
     */
    Collection<String> getPrivilegesAssignedToCurrentUser();

}
