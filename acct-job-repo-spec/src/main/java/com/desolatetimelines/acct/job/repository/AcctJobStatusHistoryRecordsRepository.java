package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord;

/**
 * Specification for the data repository that retrieves and persists
 * {@link com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord job status history records}
 */
public interface AcctJobStatusHistoryRecordsRepository {

    /**
     * Creates a new {@link AcctJobStatusHistoryRecord status history record}
     *
     * @return a reference to the created entity
     */
    AcctJobStatusHistoryRecord createNew();

    /**
     * Saves the referenced {@link AcctJobStatusHistoryRecord job status history record}
     *
     * @param acctJobStatusHistoryRecord the referenced job status history record
     * @return a reference to the saved entity
     */
    AcctJobStatusHistoryRecord save(AcctJobStatusHistoryRecord acctJobStatusHistoryRecord);

}
