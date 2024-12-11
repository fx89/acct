package com.desolatetimelines.acct.job.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJob;

@Entity
@Table(name = "\"job_status\"")
public class JpaAcctJobStatus implements AcctJobStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_status_id")
    private Long jobStatusId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JpaAcctJob job;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_status")
    JobStatus jobStatus;

    @Column(name = "first_start_date")
    Instant firstStartDate;

    @Column(name = "last_start_date")
    Instant lastStartDate;

    @Column(name = "last_end_date")
    Instant lastEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "last_outcome")
    JobOutcome lastOutcome;

    @Column(name = "current_start_date")
    Instant currentStartDate;

    @Column(name = "number_of_failures_since_last_successful_outcome")
    Integer numberOfFailuresSinceLastSuccessfulOutcome;

    public Long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Long jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Override
    public AcctJob getJob() {
        return job;
    }

    @Override
    public void setJob(AcctJob job) {
        doWithJpaAcctJob(job, jpaAcctJob -> this.job = jpaAcctJob);
    }

    public void setJob(JpaAcctJob job) {
        this.job = job;
    }

    @Override
    public JobStatus getJobStatus() {
        return jobStatus;
    }

    @Override
    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public Instant getFirstStartDate() {
        return firstStartDate;
    }

    @Override
    public void setFirstStartDate(Instant firstStartDate) {
        this.firstStartDate = firstStartDate;
    }

    @Override
    public Instant getLastStartDate() {
        return lastStartDate;
    }

    @Override
    public void setLastStartDate(Instant lastStartDate) {
        this.lastStartDate = lastStartDate;
    }

    @Override
    public Instant getLastEndDate() {
        return lastEndDate;
    }

    @Override
    public void setLastEndDate(Instant lastEndDate) {
        this.lastEndDate = lastEndDate;
    }

    @Override
    public JobOutcome getLastOutcome() {
        return lastOutcome;
    }

    @Override
    public void setLastOutcome(JobOutcome lastOutcome) {
        this.lastOutcome = lastOutcome;
    }

    @Override
    public Instant getCurrentStartDate() {
        return currentStartDate;
    }

    @Override
    public void setCurrentStartDate(Instant currentStartDate) {
        this.currentStartDate = currentStartDate;
    }

    @Override
    public Integer getNumberOfFailuresSinceLastSuccessfulOutcome() {
        return numberOfFailuresSinceLastSuccessfulOutcome;
    }

    @Override
    public void setNumberOfFailuresSinceLastSuccessfulOutcome(Integer numberOfFailuresSinceLastSuccessfulOutcome) {
        this.numberOfFailuresSinceLastSuccessfulOutcome = numberOfFailuresSinceLastSuccessfulOutcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctJobStatus that = (JpaAcctJobStatus) o;
        return Objects.equals(jobStatusId, that.jobStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobStatusId);
    }

}
