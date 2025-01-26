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

    /**
     * Returns a count of all icons that match the given name pattern and are part of the given category
     * name. If a name pattern is not given then icons with all names are counted. If a category name is
     * not given then icons from all categories are counted.
     *
     * @param iconNamePattern  the given name pattern
     * @param iconCategoryName the given category name
     */
    Long countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern,
        String iconCategoryName
    );

}
