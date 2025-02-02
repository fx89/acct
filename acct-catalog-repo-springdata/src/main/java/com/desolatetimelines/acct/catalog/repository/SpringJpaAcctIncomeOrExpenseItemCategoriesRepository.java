package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIncomeOrExpenseItemCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemCategoryReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctIncomeOrExpenseItemCategoriesRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIncomeOrExpenseItemCategoriesRepository implements AcctIncomeOrExpenseItemCategoriesRepository {

    private final JpaAcctIncomeOrExpenseItemCategoriesRepository jpaAcctIncomeOrExpenseItemCategoriesRepository;

    public SpringJpaAcctIncomeOrExpenseItemCategoriesRepository(JpaAcctIncomeOrExpenseItemCategoriesRepository jpaAcctIncomeOrExpenseItemCategoriesRepository) {
        this.jpaAcctIncomeOrExpenseItemCategoriesRepository = jpaAcctIncomeOrExpenseItemCategoriesRepository;
    }

    @Override
    public AcctIncomeOrExpenseItemCategory createNew() {
        return new JpaAcctIncomeOrExpenseItemCategory();
    }

    @Override
    public Optional<AcctIncomeOrExpenseItemCategory> findFirstByIncomeOrExpenseItemCategoryUUID(
        String incomeOrExpenseItemCategoryUUID
    ) {
        return
            jpaAcctIncomeOrExpenseItemCategoriesRepository
                .findFirstByIncomeExpenseItemCategoryUUID(incomeOrExpenseItemCategoryUUID)
                .map(identity());

    }

    @Override
    public AcctIncomeOrExpenseItemCategory save(AcctIncomeOrExpenseItemCategory incomeOrExpenseItemCategory) {
        return
            jpaAcctIncomeOrExpenseItemCategoriesRepository
                .save(
                    doWithJpaAcctIncomeOrExpenseItemCategoryReturning(
                        incomeOrExpenseItemCategory,
                        identity()
                    )
                );
    }

    @Override
    public Collection<AcctIncomeOrExpenseItemCategory> findAll() {
        return
            StreamSupport.stream(
                    jpaAcctIncomeOrExpenseItemCategoriesRepository.findAll().spliterator(),
                    false
                )
                .map(jpaAcctIncomeOrExpenseItemCategory ->
                    (AcctIncomeOrExpenseItemCategory) jpaAcctIncomeOrExpenseItemCategory
                )
                .toList();
    }

    @Override
    public Collection<AcctIncomeOrExpenseItemCategory> findByIncomeOrExpenseItemCategoryUUIDIn(
        Collection<String> incomeOrExpenseItemCategoryUUIDs
    ) {
        return
            jpaAcctIncomeOrExpenseItemCategoriesRepository
                .findByIncomeExpenseItemCategoryUUIDIn(incomeOrExpenseItemCategoryUUIDs)
                .stream()
                .map(cat -> (AcctIncomeOrExpenseItemCategory) cat)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories) {
        jpaAcctIncomeOrExpenseItemCategoriesRepository.deleteAll(
            incomeOrExpenseItemCategories.stream()
                .map(cat ->
                    doWithJpaAcctIncomeOrExpenseItemCategoryReturning(cat, identity())
                )
                .toList()
        );
    }
}
