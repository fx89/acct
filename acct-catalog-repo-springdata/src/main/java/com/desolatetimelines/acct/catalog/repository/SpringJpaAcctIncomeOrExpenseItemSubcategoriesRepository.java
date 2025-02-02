package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemCategory;
import com.desolatetimelines.acct.catalog.model.AcctIncomeOrExpenseItemSubcategory;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIncomeOrExpenseItemSubcategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemCategoryReturning;
import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctIncomeOrExpenseItemSubcategoriesRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIncomeOrExpenseItemSubcategoriesRepository implements AcctIncomeOrExpenseItemSubcategoriesRepository {

    private final JpaAcctIncomeOrExpenseItemSubcategoriesRepository jpaAcctIncomeOrExpenseItemSubcategoriesRepository;

    public SpringJpaAcctIncomeOrExpenseItemSubcategoriesRepository(JpaAcctIncomeOrExpenseItemSubcategoriesRepository jpaAcctIncomeOrExpenseItemSubcategoriesRepository) {
        this.jpaAcctIncomeOrExpenseItemSubcategoriesRepository = jpaAcctIncomeOrExpenseItemSubcategoriesRepository;
    }

    @Override
    public Collection<AcctIncomeOrExpenseItemSubcategory> findAllByByIncomeOrExpenseItemCategoryIn(
        Collection<AcctIncomeOrExpenseItemCategory> incomeOrExpenseItemCategories
    ) {
        return
            jpaAcctIncomeOrExpenseItemSubcategoriesRepository
                .findAllByIncomeExpenseItemCategoryIn(
                    incomeOrExpenseItemCategories.stream()
                        .map(cat ->
                            doWithJpaAcctIncomeOrExpenseItemCategoryReturning(cat, identity())
                        )
                        .toList()
                ).stream()
                .map(subcat -> (AcctIncomeOrExpenseItemSubcategory) subcat)
                .toList();
    }

    @Override
    public void deleteAll(Collection<AcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories) {
        jpaAcctIncomeOrExpenseItemSubcategoriesRepository.deleteAll(
            incomeOrExpenseItemSubcategories.stream()
                .map(subcat ->
                    doWithJpaAcctIncomeOrExpenseItemSubcategoryReturning(subcat, identity())
                )
                .toList()
        );
    }
}
