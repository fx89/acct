package com.desolatetimelines.acct.security.ws.endpoint.model;

import java.util.Collection;

/**
 * Groups user-owned report UUIDs by the ownership type
 *
 * @param userReports   a collection of the UUIDs of the reports owned directly by the user
 * @param groupReports  a collection of the UUIDs of the reports owned by groups that the user is part of
 * @param publicReports a collection of the UUIDs of the public reports
 */
public record OwnedReportsGroup(
    Collection<String> userReports,
    Collection<String> groupReports,
    Collection<String> publicReports
) {

    public static OwnedReportsGroupBuilder builder() {
        return new OwnedReportsGroupBuilder();
    }

    public static final class OwnedReportsGroupBuilder {
        private Collection<String> userReports;
        private Collection<String> groupReports;
        private Collection<String> publicReports;

        private OwnedReportsGroupBuilder() {
        }

        /**
         * Sets the {@code userReports} and returns a reference to this Builder enabling method chaining.
         *
         * @param userReports the {@code userReports} to set
         * @return a reference to this Builder
         */
        public OwnedReportsGroupBuilder withUserReports(Collection<String> userReports) {
            this.userReports = userReports;
            return this;
        }

        /**
         * Sets the {@code groupReports} and returns a reference to this Builder enabling method chaining.
         *
         * @param groupReports the {@code groupReports} to set
         * @return a reference to this Builder
         */
        public OwnedReportsGroupBuilder withGroupReports(Collection<String> groupReports) {
            this.groupReports = groupReports;
            return this;
        }

        /**
         * Sets the {@code publicReports} and returns a reference to this Builder enabling method chaining.
         *
         * @param publicReports the {@code publicReports} to set
         * @return a reference to this Builder
         */
        public OwnedReportsGroupBuilder withPublicReports(Collection<String> publicReports) {
            this.publicReports = publicReports;
            return this;
        }

        /**
         * Returns a {@code OwnedReportsGroup} built from the parameters previously set.
         *
         * @return a {@code OwnedReportsGroup} built with parameters of this builder
         */
        public OwnedReportsGroup build() {
            return new OwnedReportsGroup(userReports, groupReports, publicReports);
        }
    }
}
