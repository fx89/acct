package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.common.model.Page;
import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctAccountRecordAutocompleteData;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecordAutocompleteData;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctAccountRecordAutocompleteDataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountRecordAutocompleteDataReturning;
import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.doWithJpaAcctAccountReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctAccountRecordAutocompleteDataRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAcctAccountRecordAutocompleteDataRepository implements AcctAccountRecordAutocompleteDataRepository {

    private final JpaAcctAccountRecordAutocompleteDataRepository jpaAcctAccountRecordAutocompleteDataRepository;

    public SpringJpaAcctAccountRecordAutocompleteDataRepository(JpaAcctAccountRecordAutocompleteDataRepository jpaAcctAccountRecordAutocompleteDataRepository) {
        this.jpaAcctAccountRecordAutocompleteDataRepository = jpaAcctAccountRecordAutocompleteDataRepository;
    }

    @Override
    public AcctAccountRecordAutocompleteData createNew() {
        return new JpaAcctAccountRecordAutocompleteData();
    }

    @Override
    public AcctAccountRecordAutocompleteData save(AcctAccountRecordAutocompleteData autocompleteData) {
        return
            doWithJpaAcctAccountRecordAutocompleteDataReturning(
                autocompleteData,
                jpaAcctAccountRecordAutocompleteDataRepository::save
            );
    }

    @Override
    public Page<AcctAccountRecordAutocompleteData> findAllByAccountAndIncomeOrExpenseItemUUIDAndAccountRecordTextLike(
        AcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordTextPattern,
        int pageNumber,
        int pageSize
    ) {
        // Get the page
        final org.springframework.data.domain.Page<JpaAcctAccountRecordAutocompleteData> page =
            jpaAcctAccountRecordAutocompleteDataRepository
                .findAllByAccountAndIncomeExpenseItemUUIDAndAccountRecordTextLike(
                    doWithJpaAcctAccountReturning(account, identity()),
                    incomeOrExpenseItemUUID,
                    "%" + accountRecordTextPattern + "%",
                    PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(Sort.Order.asc("accountRecordText"))
                    )
                );

        // Transform the page and return a reference
        return
            new Page<>(
                page.stream()
                    .map(jpaRec -> (AcctAccountRecordAutocompleteData) jpaRec)
                    .toList(),
                page.getNumberOfElements(),
                page.getTotalElements()
            );
    }

    @Override
    public Optional<AcctAccountRecordAutocompleteData> findFirstByAccountAndIncomeOrExpenseItemUUIDAndAccountRecordText(
        AcctAccount account,
        String incomeOrExpenseItemUUID,
        String accountRecordText
    ) {
        return
            jpaAcctAccountRecordAutocompleteDataRepository
                .findFirstByAccountAndIncomeExpenseItemUUIDAndAccountRecordText(
                    doWithJpaAcctAccountReturning(account, identity()),
                    incomeOrExpenseItemUUID,
                    accountRecordText
                ).map(identity());
    }
}
