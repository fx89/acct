package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.JpaAcctIcon;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIconsRepository;
import org.springframework.stereotype.Service;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIcon;

/**
 * Implementation of the {@link AcctIconsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIconsRepository implements AcctIconsRepository {

    private final JpaAcctIconsRepository jpaAcctIconsRepository;

    public SpringJpaAcctIconsRepository(JpaAcctIconsRepository jpaAcctIconsRepository) {
        this.jpaAcctIconsRepository = jpaAcctIconsRepository;
    }

    @Override
    public AcctIcon createNew() {
        return new JpaAcctIcon();
    }

    @Override
    public AcctIcon save(AcctIcon icon) {
        return doWithJpaAcctIcon(icon, jpaAcctIconsRepository::save);
    }

}
