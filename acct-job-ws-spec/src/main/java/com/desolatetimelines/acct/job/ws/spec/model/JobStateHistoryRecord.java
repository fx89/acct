package com.desolatetimelines.acct.job.ws.spec.model;

import java.time.Instant;

/**
 * Represents a job status history record
 *
 * @param jobStatusDate   The date when the status was registered
 * @param jobStatus       The status of the job (RUNNING /IDLE)
 * @param jobOutcome      The outcome of the job (SUCCESS / FAILURE)
 * @param jobErrorMessage The optional error message in case the outcome is FAILURE
 */
public record JobStateHistoryRecord(
    Instant jobStatusDate,
    JobStatus jobStatus,
    JobOutcome jobOutcome,
    String jobErrorMessage
) {

    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@code JobStateHistoryRecord} builder static inner class.
     */
    public static final class Builder {
        private Instant jobStatusDate;
        private JobStatus jobStatus;
        private JobOutcome jobOutcome;
        private String jobErrorMessage;

        private Builder() {
        }

        /**
         * Sets the {@code jobStatusDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobStatusDate the {@code jobStatusDate} to set
         * @return a reference to this Builder
         */
        public Builder withJobStatusDate(Instant jobStatusDate) {
            this.jobStatusDate = jobStatusDate;
            return this;
        }

        /**
         * Sets the {@code jobStatus} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobStatus the {@code jobStatus} to set
         * @return a reference to this Builder
         */
        public Builder withJobStatus(JobStatus jobStatus) {
            this.jobStatus = jobStatus;
            return this;
        }

        /**
         * Sets the {@code jobOutcome} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobOutcome the {@code jobOutcome} to set
         * @return a reference to this Builder
         */
        public Builder withJobOutcome(JobOutcome jobOutcome) {
            this.jobOutcome = jobOutcome;
            return this;
        }

        /**
         * Sets the {@code jobErrorMessage} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobErrorMessage the {@code jobErrorMessage} to set
         * @return a reference to this Builder
         */
        public Builder withJobErrorMessage(String jobErrorMessage) {
            this.jobErrorMessage = jobErrorMessage;
            return this;
        }

        /**
         * Returns a {@code JobStateHistoryRecord} built from the parameters previously set.
         *
         * @return a {@code JobStateHistoryRecord} built with parameters of this {@code JobStateHistoryRecord.Builder}
         */
        public JobStateHistoryRecord build() {
            return new JobStateHistoryRecord(jobStatusDate, jobStatus, jobOutcome, jobErrorMessage);
        }
    }
}
