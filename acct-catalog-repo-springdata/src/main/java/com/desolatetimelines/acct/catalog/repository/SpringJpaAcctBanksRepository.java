package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctBank;
import com.desolatetimelines.acct.catalog.model.JpaAcctBank;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctBanksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctBankReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctBanksRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctBanksRepository implements AcctBanksRepository {

    private final JpaAcctBanksRepository jpaAcctBanksRepository;

    public SpringJpaAcctBanksRepository(JpaAcctBanksRepository jpaAcctBanksRepository) {
        this.jpaAcctBanksRepository = jpaAcctBanksRepository;
    }

    @Override
    public AcctBank createNew() {
        return new JpaAcctBank();
    }

    @Override
    public AcctBank save(AcctBank bank) {
        return doWithJpaAcctBankReturning(bank, jpaAcctBanksRepository::save);
    }

    @Override
    public Collection<AcctBank> findAll() {
        return
            StreamSupport.stream(jpaAcctBanksRepository.findAll().spliterator(), false)
                .map(jpaAcctBank -> (AcctBank) jpaAcctBank)
                .toList();
    }

    @Override
    public Optional<AcctBank> findFirstByBankUUID(String bankUUID) {
        return jpaAcctBanksRepository.findFirstByBankUUID(bankUUID).map(identity());
    }

    @Override
    public Collection<AcctBank> findAllByBankUUIDIn(Collection<String> bankUUIDs) {
        return
            jpaAcctBanksRepository.findAllByBankUUIDIn(bankUUIDs)
                .stream()
                .map(jpaAcctBank -> (AcctBank) jpaAcctBank)
                .toList();
    }

    @Override
    public Collection<AcctBank> findAllByBankIconUUIDIn(Collection<String> bankIconUUIDs) {
        return
            jpaAcctBanksRepository.findAllByBankIconUUIDIn(bankIconUUIDs)
                .stream()
                .map(jpaAcctBank -> (AcctBank) jpaAcctBank)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctBank> banks) {
        jpaAcctBanksRepository.deleteAll(
            banks.stream().map(acctBank -> doWithJpaAcctBankReturning(acctBank, identity())).toList()
        );
    }

}
