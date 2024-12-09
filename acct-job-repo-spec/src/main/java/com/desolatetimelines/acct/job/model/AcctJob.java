package com.desolatetimelines.acct.job.model;

/**
 * Uniquely identifies an ACCT job and provides access to its properties
 */
public interface AcctJob {

    String getJobUUID();

    void setJobUUID(String jobUUID);

    String getJobServiceName();

    void setJobServiceName(String jobServiceName);

    String getJobName();

    void setJobName(String jobName);

    String getJobDescription();

    void setJobDescription(String jobDescription);

}
