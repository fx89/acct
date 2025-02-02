package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItem;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIncomeOrExpenseItemsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemReturning;
import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctIncomeOrExpenseItemsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIncomeOrExpenseItemsRepository implements AcctIncomeOrExpenseItemsRepository {

    private final JpaAcctIncomeOrExpenseItemsRepository jpaAcctIncomeOrExpenseItemsRepository;

    public SpringJpaAcctIncomeOrExpenseItemsRepository(JpaAcctIncomeOrExpenseItemsRepository jpaAcctIncomeOrExpenseItemsRepository) {
        this.jpaAcctIncomeOrExpenseItemsRepository = jpaAcctIncomeOrExpenseItemsRepository;
    }

    @Override
    public Collection<AcctIncomeOrExpenseItem> findAllByIncomeOrExpenseItemSubcategoryIn(Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories) {
        return
            jpaAcctIncomeOrExpenseItemsRepository
                .findAllByIncomeExpenseItemSubcategoryIn(
                    incomeOrExpenseItemSubcategories.stream()
                        .map(subcat ->
                            doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning(subcat, identity())
                        )
                        .toList()
                )
                .stream()
                .map(item -> (AcctIncomeOrExpenseItem) item)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctIncomeOrExpenseItem> incomeOrExpenseItems) {
        jpaAcctIncomeOrExpenseItemsRepository
            .deleteAll(
                incomeOrExpenseItems.stream()
                    .map(item -> doWithJpaAcctIncomeOrExpenseItemReturning(item, identity()))
                    .toList()
            );
    }
}
