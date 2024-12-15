package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.job.model.AcctJob;
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

    /**
     * Returns a {@link Page page} of {@link AcctJobStatusHistoryRecord job status history records}
     * for the {@link AcctJob job} with the given job UUID. The data set is sorted in descending order
     * by {@link AcctJobStatusHistoryRecord#getJobStatusDate() job status date}. The given page number
     * controls the number of the page. The given page size controls the size of the page.
     *
     * @param jobUUID    the given job UUID
     * @param pageNumber the given page number
     * @param pageSize   the given page size
     */
    Page<AcctJobStatusHistoryRecord> getJobStateHistoryRecordsPage(
        String jobUUID,
        int pageNumber,
        int pageSize
    );

}
