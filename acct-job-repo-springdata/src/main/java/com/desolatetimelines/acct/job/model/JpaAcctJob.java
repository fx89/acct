package com.desolatetimelines.acct.job.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"job\"")
public class JpaAcctJob implements AcctJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_uuid")
    private String jobUUID;

    @Column(name = "job_service_name")
    private String jobServiceName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_description")
    private String jobDescription;

    public JpaAcctJob() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String getJobUUID() {
        return jobUUID;
    }

    @Override
    public void setJobUUID(String jobUUID) {
        this.jobUUID = jobUUID;
    }

    @Override
    public String getJobServiceName() {
        return jobServiceName;
    }

    @Override
    public void setJobServiceName(String jobServiceName) {
        this.jobServiceName = jobServiceName;
    }

    @Override
    public String getJobName() {
        return jobName;
    }

    @Override
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String getJobDescription() {
        return jobDescription;
    }

    @Override
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctJob that = (JpaAcctJob) o;
        return Objects.equals(jobUUID, that.jobUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobUUID);
    }
}
