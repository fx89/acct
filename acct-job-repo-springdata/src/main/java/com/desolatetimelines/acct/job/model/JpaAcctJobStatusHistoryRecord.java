package com.desolatetimelines.acct.job.model;

import jakarta.persistence.*;

import java.time.Instant;

import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJob;
import static java.util.function.Function.identity;

@Entity
@Table(name = "\"job_status_history\"")
public class JpaAcctJobStatusHistoryRecord implements AcctJobStatusHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_status_history_id")
    private Long jobStatusHistoryId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JpaAcctJob job;

    @Column(name = "job_status_date")
    private Instant jobStatusDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_status")
    JobStatus jobStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_outcome")
    JobOutcome jobOutcome;

    @Column(name = "job_error_message")
    String jobErrorMessage;

    public JpaAcctJobStatusHistoryRecord() {
    }

    public Long getJobStatusHistoryId() {
        return jobStatusHistoryId;
    }

    public void setJobStatusHistoryId(Long jobStatusHistoryId) {
        this.jobStatusHistoryId = jobStatusHistoryId;
    }

    public void setJob(JpaAcctJob job) {
        this.job = job;
    }

    @Override
    public AcctJob getJob() {
        return job;
    }

    @Override
    public void setJob(AcctJob job) {
        this.job = doWithJpaAcctJob(job, identity());
    }

    @Override
    public Instant getJobStatusDate() {
        return jobStatusDate;
    }

    @Override
    public void setJobStatusDate(Instant jobStatusDate) {
        this.jobStatusDate = jobStatusDate;
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
    public JobOutcome getJobOutcome() {
        return jobOutcome;
    }

    @Override
    public void setJobOutcome(JobOutcome jobOutcome) {
        this.jobOutcome = jobOutcome;
    }

    @Override
    public String getJobErrorMessage() {
        return jobErrorMessage;
    }

    @Override
    public void setJobErrorMessage(String jobErrorMessage) {
        this.jobErrorMessage = jobErrorMessage;
    }
}
