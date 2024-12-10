package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains the attributes of a job
 *
 * @param jobUUID        a V4 UUID that uniquely identifies the job in the ACCT ecosystem
 * @param jobServiceName the name of the service that registers the job
 * @param jobName        the name of the job
 * @param jobDescription a human-readable description of what the job does
 */
public record JobSummary(

    String jobUUID,
    String jobServiceName,
    String jobName,
    String jobDescription
) {

    public static JobSummaryBuilder builder() {
        return new JobSummaryBuilder();
    }

    /**
     * {@code JobSummary} builder static inner class.
     */
    public static final class JobSummaryBuilder {
        private String jobUUID;
        private String jobServiceName;
        private String jobName;
        private String jobDescription;

        private JobSummaryBuilder() {
        }

        /**
         * Sets the {@code jobUUID} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobUUID the {@code jobUUID} to set
         * @return a reference to this Builder
         */
        public JobSummaryBuilder withJobUUID(String jobUUID) {
            this.jobUUID = jobUUID;
            return this;
        }

        /**
         * Sets the {@code jobServiceName} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobServiceName the {@code jobServiceName} to set
         * @return a reference to this Builder
         */
        public JobSummaryBuilder withJobServiceName(String jobServiceName) {
            this.jobServiceName = jobServiceName;
            return this;
        }

        /**
         * Sets the {@code jobName} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobName the {@code jobName} to set
         * @return a reference to this Builder
         */
        public JobSummaryBuilder withJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        /**
         * Sets the {@code jobDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobDescription the {@code jobDescription} to set
         * @return a reference to this Builder
         */
        public JobSummaryBuilder withJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
            return this;
        }

        /**
         * Returns a {@code JobSummary} built from the parameters previously set.
         *
         * @return a {@code JobSummary} built with parameters of this {@code JobSummary.Builder}
         */
        public JobSummary build() {
            return new JobSummary(jobUUID, jobServiceName, jobName, jobDescription);
        }
    }
}
