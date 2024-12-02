package com.desolatetimelines.acct.security.ws.endpoint.model;

import java.util.Collection;

/**
 * Groups user-owned dashboard UUIDs by the ownership type
 *
 * @param userDashboards   a collection of the UUIDs of the dashboards owned directly by the user
 * @param groupDashboards  a collection of the UUIDs of the dashboards owned by groups that the user is part of
 * @param publicDashboards a collection of the UUIDs of the public dashboards
 */
public record OwnedDashboardsGroup(
    Collection<String> userDashboards,
    Collection<String> groupDashboards,
    Collection<String> publicDashboards
) {

    public static OwnedDashboardsGroupBuilder builder() {
        return new OwnedDashboardsGroupBuilder();
    }

    public static final class OwnedDashboardsGroupBuilder {
        private Collection<String> userDashboards;
        private Collection<String> groupDashboards;
        private Collection<String> publicDashboards;

        private OwnedDashboardsGroupBuilder() {
        }

        /**
         * Sets the {@code userDashboards} and returns a reference to this Builder enabling method chaining.
         *
         * @param userDashboards the {@code userDashboards} to set
         * @return a reference to this Builder
         */
        public OwnedDashboardsGroupBuilder withUserDashboards(Collection<String> userDashboards) {
            this.userDashboards = userDashboards;
            return this;
        }

        /**
         * Sets the {@code groupDashboards} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupDashboards the {@code groupDashboards} to set
         * @return a reference to this Builder
         */
        public OwnedDashboardsGroupBuilder withGroupDashboards(Collection<String> groupDashboards) {
            this.groupDashboards = groupDashboards;
            return this;
        }

        /**
         * Sets the {@code publicDashboards} and returns a reference to this Builder enabling method chaining.
         *
         * @param publicDashboards the {@code publicDashboards} to set
         * @return a reference to this Builder
         */
        public OwnedDashboardsGroupBuilder withPublicDashboards(Collection<String> publicDashboards) {
            this.publicDashboards = publicDashboards;
            return this;
        }

        /**
         * Returns a {@code OwnedDashboardsGroup} built from the parameters previously set.
         *
         * @return a {@code OwnedDashboardsGroup} built with parameters of this builder
         */
        public OwnedDashboardsGroup build() {
            return new OwnedDashboardsGroup(userDashboards, groupDashboards, publicDashboards);
        }
    }
}
