package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import com.desolatetimelines.acct.workspace.springrepository.JpaAccountRecordsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AccountRecordsRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAccountRecordsRepository implements AccountRecordsRepository {

    private final JpaAccountRecordsRepository jpaAccountRecordsRepository;

    public SpringJpaAccountRecordsRepository(JpaAccountRecordsRepository jpaAccountRecordsRepository) {
        this.jpaAccountRecordsRepository = jpaAccountRecordsRepository;
    }

    @Override
    public AcctAccountRecord createNew() {
        return new JpaAcctAccountRecord();
    }

    @Override
    public AcctAccountRecord save(AcctAccountRecord accountRecord) {
        return doWithJpaAcctAccountRecordReturning(accountRecord, jpaAccountRecordsRepository::save);
    }

    @Override
    public Optional<AcctAccountRecord> findFirstByAccountRecordId(Long accountRecordId) {
        return jpaAccountRecordsRepository.findById(accountRecordId).map(identity());
    }
}
