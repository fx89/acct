package com.desolatetimelines.acct.security.ws.endpoint;

import com.desolatetimelines.acct.security.ws.endpoint.model.OwnedReportsGroup;
import com.desolatetimelines.acct.security.ws.endpoint.model.OwnerType;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportAccessibilityReport;
import com.desolatetimelines.acct.security.ws.endpoint.model.ReportOwner;

import java.util.Collection;

/**
 * Specification for the report ownership endpoint. Defines both client and server functionality.
 */
public interface ReportOwnershipEndpoint {

    /**
     * Returns a set of UUIDs of the reports owned by the owner of the given owner type
     * having the given owner UUID
     *
     * @param ownerType the given owner type
     * @param ownerUUID the given owner UUID
     */
    Collection<String> getReportsOwnedByOwnerOfType(OwnerType ownerType, String ownerUUID);

    /**
     * Returns a group of collections containing the UUIDs of the reports accessible to the user
     * via each possible ownership type: <ul>
     * <li>{@link OwnerType#USER accessible to the user}</li>
     * <li>{@link OwnerType#GROUP accessible to the user's group}</li>
     * <li>{@link OwnerType#PUBLIC accessible to everyone}</li>
     * </ul>
     *
     * @param userUUID the UUID of the user whose resources are being queried
     */
    OwnedReportsGroup getUserAccessibleReports(String userUUID);

    /**
     * Creates a new report ownership record with the provider details
     *
     * @param reportOwner container for the provided details
     */
    void addReportOwner(ReportOwner reportOwner);

    /**
     * Deletes the report owner of the given owner type having the given owner UUID
     * and the given reportUUID
     *
     * @param ownerType  the given owner type
     * @param ownerUUID  the given owner UUID
     * @param reportUUID the given report UUID
     */
    void deleteReportOwner(OwnerType ownerType, String ownerUUID, String reportUUID);

    /**
     * Checks the accessibility of the report identified by the given report UUID
     * to the user identified by the given user UUID
     *
     * @param userUUID   the given user UUID
     * @param reportUUID the given report UUID
     * @return the accessibility report
     */
    ReportAccessibilityReport isUserAccessibleReport(String userUUID, String reportUUID);

}
