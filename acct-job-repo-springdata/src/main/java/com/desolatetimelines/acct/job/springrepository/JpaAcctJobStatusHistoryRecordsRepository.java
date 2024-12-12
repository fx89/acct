package com.desolatetimelines.acct.job.springrepository;

import com.desolatetimelines.acct.job.model.JpaAcctJobStatusHistoryRecord;
import org.springframework.data.repository.CrudRepository;

public interface JpaAcctJobStatusHistoryRecordsRepository extends CrudRepository<JpaAcctJobStatusHistoryRecord, Long> {
}
