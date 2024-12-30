package com.desolatetimelines.acct.workspace.privilegesprovider.model;

/**
 * Enumerates the type of ownership that can exist on a resource that a user might attempt to access: <ul>
 * <li><b>OWN_RESOURCES</b> = user attempts to access directly-owned resources</li>
 * <li><b>GROUP_RESOURCES</b> = user attempts to access resources belonging to a group that the user is part of</li>
 * <li><b>ANY_RESOURCES</b> = user attempts to access resources that are not owned by the user in any way</li>
 * </ul>
 */
public enum ResourceOwnership {
    OWN_RESOURCES,
    GROUP_RESOURCES,
    ANY_RESOURCES
}
