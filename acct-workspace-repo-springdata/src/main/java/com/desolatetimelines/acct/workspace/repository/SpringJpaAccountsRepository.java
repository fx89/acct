package com.desolatetimelines.acct.workspace.repository;

import com.desolatetimelines.acct.workspace.model.AcctAccount;
import com.desolatetimelines.acct.workspace.model.AcctWorkspace;
import com.desolatetimelines.acct.workspace.model.JpaAcctAccount;
import com.desolatetimelines.acct.workspace.springrepository.JpaAcctAccountsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.desolatetimelines.acct.workspace.util.AcctWorkspaceRepoSpringDataUtils.*;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AccountsRepository} using Spring Data JPA
 */
@Service
public class SpringJpaAccountsRepository implements AccountsRepository {

    private final JpaAcctAccountsRepository jpaAcctAccountsRepository;

    public SpringJpaAccountsRepository(JpaAcctAccountsRepository jpaAcctAccountsRepository) {
        this.jpaAcctAccountsRepository = jpaAcctAccountsRepository;
    }

    @Override
    public AcctAccount createNew() {
        return new JpaAcctAccount();
    }

    @Override
    public Optional<AcctAccount> findFirstByAccountUUID(String accountUUID) {
        return jpaAcctAccountsRepository.findFirstByAccountUUID(accountUUID).map(identity());
    }

    @Override
    public Collection<AcctAccount> findAllByAccountIconUUIDIn(Collection<String> accountIconUUIDs) {
        return
            jpaAcctAccountsRepository.findAllByAccountIconUUIDIn(accountIconUUIDs)
                .stream()
                .map(acct -> (AcctAccount) acct)
                .toList();
    }

    @Override
    public Collection<AcctAccount> findAllByBankUUIDIn(Collection<String> bankUUIDs) {
        return
            jpaAcctAccountsRepository.findAllByBankUUIDIn(bankUUIDs)
                .stream()
                .map(acct -> (AcctAccount) acct)
                .toList();
    }

    @Override
    public Collection<AcctAccount> findAllByCurrencyUUIDIn(Collection<String> currencyUUIDs) {
        return
            jpaAcctAccountsRepository.findAllByCurrencyUUIDIn(currencyUUIDs)
                .stream()
                .map(acct -> (AcctAccount) acct)
                .toList();
    }

    @Override
    public AcctAccount saveAccount(AcctAccount account) {
        return doWithJpaAcctAccountReturning(account, jpaAcctAccountsRepository::save);
    }

    @Override
    public Collection<AcctAccount> findAllByWorkspace(AcctWorkspace workspace) {
        return
            jpaAcctAccountsRepository
                .findAllByWorkspace(
                    doWithJpaAcctWorkspaceReturning(workspace, identity())
                )
                .stream()
                .map(jpaAcctAccount -> (AcctAccount) jpaAcctAccount)
                .toList();
    }

    @Override
    public void deleteAccount(AcctAccount account) {
        doWithJpaAcctAccount(account, jpaAcctAccountsRepository::delete);
    }
}
