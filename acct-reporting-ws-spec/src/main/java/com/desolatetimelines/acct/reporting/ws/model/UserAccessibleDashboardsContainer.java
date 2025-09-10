package com.desolatetimelines.acct.reporting.ws.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Container for the two groups of dashboards that are accessible to a user
 *
 * @param userDashboards  Dashboards that have been created by the user and therefore directly owned by the user
 * @param groupDashboards Dashboards that are accessible to a group that the user is part of
 */
public record UserAccessibleDashboardsContainer(
    Set<DashboardReadableProperties> userDashboards,
    Set<DashboardReadableProperties> groupDashboards
) {

    public static UserAccessibleDashboardsContainerBuilder builder() {
        return new UserAccessibleDashboardsContainerBuilder();
    }

    public static final class UserAccessibleDashboardsContainerBuilder {
        private final Set<DashboardReadableProperties> userDashboards = new HashSet<>();
        private final Set<DashboardReadableProperties> groupDashboards = new HashSet<>();

        private UserAccessibleDashboardsContainerBuilder() {
        }

        public UserAccessibleDashboardsContainerBuilder withUserDashboard(DashboardReadableProperties userDashboard) {
            this.userDashboards.add(userDashboard);
            return this;
        }

        public UserAccessibleDashboardsContainerBuilder withGroupDashboard(DashboardReadableProperties groupDashboard) {
            this.groupDashboards.add(groupDashboard);
            return this;
        }

        public UserAccessibleDashboardsContainer build() {
            return new UserAccessibleDashboardsContainer(userDashboards, groupDashboards);
        }
    }
}
