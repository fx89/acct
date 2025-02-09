package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItem;
import com.desolatetimelines.acct.catalog.model.JpaAcctIncomeOrExpenseItemSubcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JpaAcctIncomeOrExpenseItemsRepository extends CrudRepository<JpaAcctIncomeOrExpenseItem, Long> {

    Collection<JpaAcctIncomeOrExpenseItem> findAllByIncomeExpenseItemSubcategoryIn(
        Collection<JpaAcctIncomeOrExpenseItemSubcategory> incomeOrExpenseItemSubcategories
    );

    Optional<JpaAcctIncomeOrExpenseItem> findFirstByIncomeExpenseItemUUID(String incomeExpenseItemUUID);

    Collection<JpaAcctIncomeOrExpenseItem> findAllByIncomeExpenseItemUUIDIn(Collection<String> incomeExpenseItemUUIDs);

}
