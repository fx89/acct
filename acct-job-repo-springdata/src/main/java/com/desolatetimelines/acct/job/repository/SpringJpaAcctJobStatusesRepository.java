package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.AcctJobStatus;
import com.desolatetimelines.acct.job.model.JpaAcctJob;
import com.desolatetimelines.acct.job.model.JpaAcctJobStatus;
import com.desolatetimelines.acct.job.springrepository.JpaAcctJobStatusesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJob;
import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJobStatus;
import static java.util.function.Function.identity;

@Service
public class SpringJpaAcctJobStatusesRepository implements AcctJobStatusesRepository {

    private final JpaAcctJobStatusesRepository jpaAcctJobStatusesRepository;

    public SpringJpaAcctJobStatusesRepository(JpaAcctJobStatusesRepository jpaAcctJobStatusesRepository) {
        this.jpaAcctJobStatusesRepository = jpaAcctJobStatusesRepository;
    }

    @Override
    public Optional<AcctJobStatus> findFirstByJob(AcctJob job) {
        final JpaAcctJob jpaAcctJob = doWithJpaAcctJob(job, identity());
        return jpaAcctJobStatusesRepository.findFirstByJob(jpaAcctJob).map(identity());
    }

    @Override
    public AcctJobStatus createNew() {
        return new JpaAcctJobStatus();
    }

    @Override
    public AcctJobStatus save(AcctJobStatus jobStatus) {
        return doWithJpaAcctJobStatus(jobStatus, jpaAcctJobStatusesRepository::save);
    }
}
