package com.desolatetimelines.acct.job.repository;

import com.desolatetimelines.acct.job.model.AcctJob;
import com.desolatetimelines.acct.job.model.JpaAcctJob;
import com.desolatetimelines.acct.job.springrepository.JpaAcctJobsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.job.util.AcctJobRepoSpringDataUtils.doWithJpaAcctJob;
import static java.util.function.Function.identity;

@Service
public class SpringJpaAcctJobsRepository implements AcctJobsRepository {

    private final JpaAcctJobsRepository jpaAcctJobsRepository;

    public SpringJpaAcctJobsRepository(JpaAcctJobsRepository jpaAcctJobsRepository) {
        this.jpaAcctJobsRepository = jpaAcctJobsRepository;
    }

    @Override
    public Optional<AcctJob> findFirstByJobUUID(String jobUUID) {
        return jpaAcctJobsRepository.findFirstByJobUUID(jobUUID).map(identity());
    }

    @Override
    public AcctJob createNew() {
        return new JpaAcctJob();
    }

    @Override
    public AcctJob save(AcctJob job) {
        return doWithJpaAcctJob(job, jpaAcctJobsRepository::save);
    }

    @Override
    public Set<AcctJob> findAll() {
        return
            StreamSupport.stream(jpaAcctJobsRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
