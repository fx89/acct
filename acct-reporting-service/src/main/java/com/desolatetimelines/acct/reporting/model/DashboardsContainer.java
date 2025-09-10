package com.desolatetimelines.acct.reporting.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public record DashboardsContainer(
    Set<DashboardReadableProperties> userDashboards,
    Set<DashboardReadableProperties> groupDashboards
) {

    public static DashboardsContainerBuilder builder() {
        return new DashboardsContainerBuilder();
    }

    public static final class DashboardsContainerBuilder {
        private final Set<DashboardReadableProperties> userDashboards = new HashSet<>();
        private final Set<DashboardReadableProperties> groupDashboards = new HashSet<>();

        private DashboardsContainerBuilder() {
        }

        public DashboardsContainerBuilder withUserDashboard(DashboardReadableProperties userDashboard) {
            this.userDashboards.add(userDashboard);
            return this;
        }

        public DashboardsContainerBuilder withGroupDashboard(DashboardReadableProperties groupDashboard) {
            this.groupDashboards.add(groupDashboard);
            return this;
        }

        public DashboardsContainerBuilder withUserDashboards(Collection<DashboardReadableProperties> userDashboards) {
            this.userDashboards.addAll(userDashboards);
            return this;
        }

        public DashboardsContainerBuilder withGroupDashboards(Collection<DashboardReadableProperties> groupDashboards) {
            this.groupDashboards.addAll(groupDashboards);
            return this;
        }

        public DashboardsContainer build() {
            return new DashboardsContainer(userDashboards, groupDashboards);
        }
    }
}
