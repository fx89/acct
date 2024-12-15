package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.job.model.AcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.model.JpaAcctJobStatusHistoryRecord;
import com.desolatetimelines.acct.job.springrepository.JpaAcctJobStatusHistoryRecordsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<AcctJobStatusHistoryRecord> getJobStateHistoryRecordsPage(
        String jobUUID,
        int pageNumber,
        int pageSize
    ) {
        // Define the page request
        final Pageable pageRequest =
            PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("jobStatusDate")));

        // Get the page
        final org.springframework.data.domain.Page<JpaAcctJobStatusHistoryRecord> page =
            jpaAcctJobStatusHistoryRecordsRepository.findByJobJobUUID(jobUUID, pageRequest);

        // Map the page
        return
            new Page<>(
                page.get().map(rec -> (AcctJobStatusHistoryRecord) rec).toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

}
