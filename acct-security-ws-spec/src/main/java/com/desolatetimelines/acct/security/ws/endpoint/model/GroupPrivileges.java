package com.desolatetimelines.acct.security.ws.endpoint.model;

import java.util.Collection;

/**
 * Defines a set of privileges that should be assigned to a group
 *
 * @param groupUUID      the UUID of the group to which the privileges are to be assigned
 * @param privilegeNames the set of privileges
 */
public record GroupPrivileges(

    String groupUUID,

    Collection<String> privilegeNames

) {
}
