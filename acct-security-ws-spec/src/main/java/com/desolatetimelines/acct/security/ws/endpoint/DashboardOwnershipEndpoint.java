package com.desolatetimelines.acct.security.ws.endpoint;

import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.DashboardOwner;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedDashboardsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;

import java.util.Collection;

/**
 * Specification for the dashboard ownership endpoint. Defines both client and server functionality.
 */
public interface DashboardOwnershipEndpoint {

    /**
     * Returns a set of UUIDs of the dashboards owned by the owner of the given owner type
     * having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    Collection<String> getDashboardsOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID);

    /**
     * Returns a group of collections containing the UUIDs of the dashboards accessible to the user
     * via each possible ownership type: <ul>
     * <li>{@link OwnerType#USER accessible to the user}</li>
     * <li>{@link OwnerType#GROUP accessible to the user's group}</li>
     * <li>{@link OwnerType#PUBLIC accessible to everyone}</li>
     * </ul>
     *
     * @param userUUID the UUID of the user whose resources are being queried
     */
    OwnedDashboardsGroup getUserAccessibleDashboards(String userUUID);

    /**
     * Creates a new dashboard ownership record with the provider details
     *
     * @param dashboardOwner container for the provided details
     */
    void addDashboardOwner(DashboardOwner dashboardOwner);

    /**
     * Deletes the dashboard owner of the given owner type having the given owner UUID
     * and the given dashboardUUID
     *
     * @param ownerType     the given owner type
     * @param ownerUUID     the given owner UUID
     * @param dashboardUUID the given dashboard UUID
     */
    void deleteDashboardOwner(OwnerType ownerType, String ownerUUID, String dashboardUUID);

    /**
     * Checks the accessibility of the dashboard identified by the given dashboard UUID
     * to the user identified by the given user UUID
     *
     * @param userUUID      the given user UUID
     * @param dashboardUUID the given dashboard UUID
     * @return the accessibility report
     */
    DashboardAccessibilityReport isUserAccessibleDashboard(String userUUID, String dashboardUUID);

}
