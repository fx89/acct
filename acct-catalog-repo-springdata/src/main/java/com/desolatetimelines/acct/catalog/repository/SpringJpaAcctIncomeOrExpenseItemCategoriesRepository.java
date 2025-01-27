package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIncomeOrExpenseItemCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
