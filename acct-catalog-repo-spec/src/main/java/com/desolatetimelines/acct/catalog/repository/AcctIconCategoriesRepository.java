package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIconCategory;

import java.util.Optional;

/**
 * Repository for loading and persisting {@link AcctIconCategory icon categories}
 */
public interface AcctIconCategoriesRepository {

    /**
     * Creates a new {@link AcctIconCategory icon category}
     *
     * @return a reference to the newly created entity
     */
    AcctIconCategory createNew();

    /**
     * Persists the referenced icon category
     *
     * @param iconCategory the referenced icon category
     * @return a reference to the persisted entity
     */
    AcctIconCategory save(AcctIconCategory iconCategory);

    /**
     * Returns a reference to the {@link AcctIconCategory icon category} with the given name
     * or, if such an icon category does not exist, an empty optional
     *
     * @param iconCategoryName the given name
     */
    Optional<AcctIconCategory> findByIconCategoryName(String iconCategoryName);

}
