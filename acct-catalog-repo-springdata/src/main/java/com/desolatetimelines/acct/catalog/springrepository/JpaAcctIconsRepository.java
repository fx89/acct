package com.desolatetimelines.acct.catalog.springrepository;

import com.desolatetimelines.acct.catalog.model.JpaAcctIcon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface JpaAcctIconsRepository extends CrudRepository<JpaAcctIcon, Long> {

    @Query("""
            select
                count(*)
            from
                JpaAcctIcon i
            where
                  (:iconNamePattern is null or i.iconName like :iconNamePattern)
              and (:iconCategoryName is null or i.iconCategory.iconCategoryName = :iconCategoryName)
        """)
    Long countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        @Param(value = "iconNamePattern") String iconNamePattern,
        @Param(value = "iconCategoryName") String iconCategoryName
    );

}
