package com.desolatetimelines.acct.security.model;

/**
 * Result of verifying a resource's accessibility to a given user
 *
 * @param accessible      true / false - tells if the user can access the resource
 * @param isGroupResource true / false - tells if the access is given via a users group
 */
public record AccessibilityReport(
    boolean accessible,
    boolean isGroupResource
) {

    /**
     * The resource is directly accessible to the user
     */
    public static final AccessibilityReport DIRECT_OWNERSHIP_REPORT =
        new AccessibilityReport(true, false);

    /**
     * The resource is accessible to the user because the user is part of a group that has access to the resource
     */
    public static final AccessibilityReport GROUP_OWNERSHIP_REPORT =
        new AccessibilityReport(true, true);

    /**
     * The resource is not accessible to the user
     */
    public static final AccessibilityReport NEGATIVE_OWNERSHIP_REPORT =
        new AccessibilityReport(false, false);

}
