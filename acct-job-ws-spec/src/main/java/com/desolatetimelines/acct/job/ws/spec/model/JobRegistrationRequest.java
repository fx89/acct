package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains the attributes required for registering a job
 *
 * @param jobServiceName the name of the service that registers the job
 * @param jobName        the name of the job
 * @param jobDescription a human-readable description of what the job does
 */
public record JobRegistrationRequest(
    String jobServiceName,
    String jobName,
    String jobDescription
) {

    public static JobRegistrationRequestBuilder builder() {
        return new JobRegistrationRequestBuilder();
    }

    /**
     * {@code JobRegistrationRequest} builder static inner class.
     */
    public static final class JobRegistrationRequestBuilder {
        private String jobServiceName;
        private String jobName;
        private String jobDescription;

        private JobRegistrationRequestBuilder() {
        }

        /**
         * Sets the {@code jobServiceName} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobServiceName the {@code jobServiceName} to set
         * @return a reference to this Builder
         */
        public JobRegistrationRequestBuilder withJobServiceName(String jobServiceName) {
            this.jobServiceName = jobServiceName;
            return this;
        }

        /**
         * Sets the {@code jobName} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobName the {@code jobName} to set
         * @return a reference to this Builder
         */
        public JobRegistrationRequestBuilder withJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        /**
         * Sets the {@code jobDescription} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobDescription the {@code jobDescription} to set
         * @return a reference to this Builder
         */
        public JobRegistrationRequestBuilder withJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
            return this;
        }

        /**
         * Returns a {@code JobRegistrationRequest} built from the parameters previously set.
         *
         * @return a {@code JobRegistrationRequest} built with parameters of this {@code JobRegistrationRequest.Builder}
         */
        public JobRegistrationRequest build() {
            return new JobRegistrationRequest(jobServiceName, jobName, jobDescription);
        }
    }
}
