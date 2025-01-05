package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecord;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import com.desolatetimelines.acct.workspace.springrepository.JpaAccountRecordsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<AcctAccountRecord> findAllByAccount(AcctAccount account, int pageNumber, int pageSize) {
        // Get the page
        final org.springframework.data.domain.Page<AcctAccountRecord> page =
            jpaAccountRecordsRepository
                .findAllByAccount(
                    account,
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(Sort.Order.asc("accountRecordDate"))
                    )
                );

        // Transform the page and return a reference
        return
            new Page<>(
                page.stream().toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public Page<AcctAccountRecord> findAllByAccountAndTextLike(
        AcctAccount account,
        String textPattern,
        int pageNumber,
        int pageSize
    ) {
        // Get the page
        final org.springframework.data.domain.Page<AcctAccountRecord> page =
            jpaAccountRecordsRepository
                .findAllByAccountAndAccountRecordTextLike(
                    account,
                    textPattern,
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(Sort.Order.asc("accountRecordDate"))
                    )
                );

        // Transform the page and return a reference
        return
            new Page<>(
                page.stream().toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }
}
