package com.desolatetimelines.acct.job.ws.mapper;

import com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.ws.spec.model.JobStateHistoryRecord;

import static com.desolatetimelines.acct.job.ws.mapper.JobStateMapper.mapJobOutcome;
import static com.desolatetimelines.acct.job.ws.mapper.JobStateMapper.mapJobStatus;

/**
 * Provides mapper methods for the {@link JobStateHistoryRecord} type
 */
public class JobStateHistoryRecordMapper {

    public static JobStateHistoryRecord fromAcctJobStatusHistoryRecord(
        AcctJobStatusHistoryRecord acctJobStatusHistoryRecord
    ) {
        return
            JobStateHistoryRecord.builder()
                .withJobOutcome(mapJobOutcome(acctJobStatusHistoryRecord.getJobOutcome()))
                .withJobErrorMessage(acctJobStatusHistoryRecord.getJobErrorMessage())
                .withJobStatusDate(acctJobStatusHistoryRecord.getJobStatusDate())
                .withJobStatus(mapJobStatus(acctJobStatusHistoryRecord.getJobStatus()))
                .build();
    }

}
