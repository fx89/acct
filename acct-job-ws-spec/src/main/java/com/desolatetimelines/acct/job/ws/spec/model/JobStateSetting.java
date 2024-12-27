package com.desolatetimelines.acct.job.ws.spec.model;

/**
 * Contains properties used to set the current state of a given job
 *
 * @param jobOutcome   the outcome of the last run of the job (SUCCESS / FAILURE)
 * @param errorMessage optional error message in case of failure
 */
public record JobStateSetting(
    JobOutcome jobOutcome,
    String errorMessage
) {

    public static JobStateSettingBuilder builder() {
        return new JobStateSettingBuilder();
    }

    /**
     * {@code JobStateSetting} builder static inner class.
     */
    public static final class JobStateSettingBuilder {
        private JobOutcome jobOutcome;
        private String errorMessage;

        private JobStateSettingBuilder() {
        }

        /**
         * Sets the {@code jobOutcome} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobOutcome the {@code jobOutcome} to set
         * @return a reference to this Builder
         */
        public JobStateSettingBuilder withJobOutcome(JobOutcome jobOutcome) {
            this.jobOutcome = jobOutcome;
            return this;
        }

        /**
         * Sets the {@code errorMessage} and returns a reference to this Builder enabling method chaining.
         *
         * @param errorMessage the {@code errorMessage} to set
         * @return a reference to this Builder
         */
        public JobStateSettingBuilder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        /**
         * Returns a {@code JobStateSetting} built from the parameters previously set.
         *
         * @return a {@code JobStateSetting} built with parameters of this {@code JobStateSetting.Builder}
         */
        public JobStateSetting build() {
            return new JobStateSetting(jobOutcome, errorMessage);
        }
    }
}
