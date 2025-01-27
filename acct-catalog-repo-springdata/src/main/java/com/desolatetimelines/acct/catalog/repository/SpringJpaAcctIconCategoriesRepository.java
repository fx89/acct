package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIconCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIconCategory;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIconCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIconCategoryReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctIconCategoriesRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIconCategoriesRepository implements AcctIconCategoriesRepository {

    private final JpaAcctIconCategoriesRepository jpaAcctIconCategoriesRepository;

    public SpringJpaAcctIconCategoriesRepository(JpaAcctIconCategoriesRepository jpaAcctIconCategoriesRepository) {
        this.jpaAcctIconCategoriesRepository = jpaAcctIconCategoriesRepository;
    }

    @Override
    public AcctIconCategory createNew() {
        return new JpaAcctIconCategory();
    }

    @Override
    public AcctIconCategory save(AcctIconCategory iconCategory) {
        return doWithJpaAcctIconCategoryReturning(iconCategory, jpaAcctIconCategoriesRepository::save);
    }

    @Override
    public Set<AcctIconCategory> findAll() {
        return
            StreamSupport.stream(jpaAcctIconCategoriesRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }


    @Override
    public Optional<AcctIconCategory> findByIconCategoryName(String iconCategoryName) {
        return jpaAcctIconCategoriesRepository.findFirstByIconCategoryName(iconCategoryName).map(identity());
    }

}
