package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIconCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaAcctIconCategoriesRepository extends CrudRepository<JpaAcctIconCategory, Long> {

    Optional<JpaAcctIconCategory> findFirstByIconCategoryName(String iconCategoryName);

}
