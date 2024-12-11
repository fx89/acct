package com.desolatetimelines.acct.job.ws.spec.model;


import java.time.Instant;

/**
 * Represents the current state of a job
 *
 * @param jobStatus                                  the current status of the job (IDLE / RUNNING)
 * @param firstStartDate                             the date when the job was first started since it was created
 * @param lastStartDate                              the date when the job was last started
 * @param lastEndDate                                the date when the job finished the last run
 * @param lastOutcome                                the outcome of the last run (SUCCESS / FAILURE)
 * @param currentStartDate                           the date when the current run started
 * @param numberOfFailuresSinceLastSuccessfulOutcome the number of times the job outcome was FAILURE since the last time when the outcome was SUCCESS
 */
public record JobState(
    JobStatus jobStatus,
    Instant firstStartDate,
    Instant lastStartDate,
    Instant lastEndDate,
    JobOutcome lastOutcome,
    Instant currentStartDate,
    Integer numberOfFailuresSinceLastSuccessfulOutcome
) {

    public static JobStateBuilder builder() {
        return new JobStateBuilder();
    }

    /**
     * {@code JobState} builder static inner class.
     */
    public static final class JobStateBuilder {
        private JobStatus jobStatus;
        private Instant firstStartDate;
        private Instant lastStartDate;
        private Instant lastEndDate;
        private JobOutcome lastOutcome;
        private Instant currentStartDate;
        private Integer numberOfFailuresSinceLastSuccessfulOutcome;

        private JobStateBuilder() {
        }

        /**
         * Sets the {@code jobStatus} and returns a reference to this Builder enabling method chaining.
         *
         * @param jobStatus the {@code jobStatus} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withJobStatus(JobStatus jobStatus) {
            this.jobStatus = jobStatus;
            return this;
        }

        /**
         * Sets the {@code firstStartDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param firstStartDate the {@code firstStartDate} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withFirstStartDate(Instant firstStartDate) {
            this.firstStartDate = firstStartDate;
            return this;
        }

        /**
         * Sets the {@code lastStartDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastStartDate the {@code lastStartDate} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withLastStartDate(Instant lastStartDate) {
            this.lastStartDate = lastStartDate;
            return this;
        }

        /**
         * Sets the {@code lastEndDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastEndDate the {@code lastEndDate} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withLastEndDate(Instant lastEndDate) {
            this.lastEndDate = lastEndDate;
            return this;
        }

        /**
         * Sets the {@code lastOutcome} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastOutcome the {@code lastOutcome} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withLastOutcome(JobOutcome lastOutcome) {
            this.lastOutcome = lastOutcome;
            return this;
        }

        /**
         * Sets the {@code currentStartDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param currentStartDate the {@code currentStartDate} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withCurrentStartDate(Instant currentStartDate) {
            this.currentStartDate = currentStartDate;
            return this;
        }

        /**
         * Sets the {@code numberOfFailuresSinceLastSuccessfulOutcome} and returns a reference to this Builder enabling method chaining.
         *
         * @param numberOfFailuresSinceLastSuccessfulOutcome the {@code numberOfFailuresSinceLastSuccessfulOutcome} to set
         * @return a reference to this Builder
         */
        public JobStateBuilder withNumberOfFailuresSinceLastSuccessfulOutcome(Integer numberOfFailuresSinceLastSuccessfulOutcome) {
            this.numberOfFailuresSinceLastSuccessfulOutcome = numberOfFailuresSinceLastSuccessfulOutcome;
            return this;
        }

        /**
         * Returns a {@code JobState} built from the parameters previously set.
         *
         * @return a {@code JobState} built with parameters of this {@code JobState.Builder}
         */
        public JobState build() {
            return new JobState(
                jobStatus,
                firstStartDate,
                lastStartDate,
                lastEndDate,
                lastOutcome,
                currentStartDate,
                numberOfFailuresSinceLastSuccessfulOutcome
            );
        }
    }
}
