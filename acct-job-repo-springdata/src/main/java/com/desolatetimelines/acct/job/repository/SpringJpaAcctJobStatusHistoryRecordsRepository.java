package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.model.JpaAcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.springrepository.JpaAcctJobStatusHistoryRecordsRepository;
import org.springframework.stereotype.Service;

import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJobStatusHistoryRecord;

@Service
public class SpringJpaAcctJobStatusHistoryRecordsRepository implements AcctJobStatusHistoryRecordsRepository {

    private final JpaAcctJobStatusHistoryRecordsRepository jpaAcctJobStatusHistoryRecordsRepository;

    public SpringJpaAcctJobStatusHistoryRecordsRepository(JpaAcctJobStatusHistoryRecordsRepository jpaAcctJobStatusHistoryRecordsRepository) {
        this.jpaAcctJobStatusHistoryRecordsRepository = jpaAcctJobStatusHistoryRecordsRepository;
    }

    @Override
    public AcctJobStatusHistoryRecord createNew() {
        return new JpaAcctJobStatusHistoryRecord();
    }

    @Override
    public AcctJobStatusHistoryRecord save(AcctJobStatusHistoryRecord acctJobStatusHistoryRecord) {
        return
            doWithJpaAcctJobStatusHistoryRecord(
                acctJobStatusHistoryRecord,
                jpaAcctJobStatusHistoryRecordsRepository::save
            );
    }

}
