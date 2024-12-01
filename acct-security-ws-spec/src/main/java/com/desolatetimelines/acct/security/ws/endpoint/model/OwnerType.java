package com.desolatetimelines.acct.security.ws.endpoint.model;

/**
 * Enumerates the types of owners that can be referenced in HTTP requests towards the Security service
 */
public enum OwnerType {
    /**
     * The user owns or has direct access to the resource
     */
    USER,

    /**
     * One of the groups that the user is part of has access to the resource
     */
    GROUP,

    /**
     * The resource is accessible to everyone
     */
    PUBLIC,

    /**
     * Any of the other owner types
     */
    ANY
}
