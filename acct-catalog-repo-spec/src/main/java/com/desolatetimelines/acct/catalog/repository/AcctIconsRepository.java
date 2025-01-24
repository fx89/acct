package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIcon;

/**
 * Repository for loading and persisting {@link AcctIcon icons}
 */
public interface AcctIconsRepository {

    /**
     * Creates a new instance of {@link AcctIcon}
     *
     * @return a reference to the newly created entity
     */
    AcctIcon createNew();

    /**
     * Persists the referenced {@link AcctIcon icon}
     *
     * @param icon the referenced icon
     * @return a reference to the persisted entity
     */
    AcctIcon save(AcctIcon icon);

}
