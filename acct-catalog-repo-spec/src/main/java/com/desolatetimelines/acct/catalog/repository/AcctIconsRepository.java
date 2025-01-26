package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.common.model.Page;

import java.util.Collection;
import java.util.Optional;

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

    /**
     * Returns a page of all icons that match the given name pattern and are part of the given category
     * name. If a name pattern is not given then icons with all names are counted. If a category name is
     * not given then icons from all categories are counted.
     *
     * @param iconNamePattern  the given name pattern
     * @param iconCategoryName the given category name
     * @param pageNumber       the 0-based number of the page to be returned
     * @param pageSize         the size of the page to be returned
     */
    Page<AcctIcon> findAllByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern,
        String iconCategoryName,
        int pageNumber,
        int pageSize
    );

    /**
     * Returns the {@link AcctIcon icon} with the given {@link AcctIcon#getIconUUID() icon UUID}
     * or an empty optional
     *
     * @param iconUUID the given icon UUID
     */
    Optional<AcctIcon> findFirstIconByIconUUID(String iconUUID);

    /**
     * Returns a collection of {@link AcctIcon icons} for which the {@link AcctIcon#getIconUUID() UUID}
     * can be found in the given collection of icon UUIDs
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    Collection<AcctIcon> findAllByIconUUIDIn(Collection<String> iconUUIDs);

    /**
     * Deletes all the icons in the given collection of icons
     *
     * @param icons the given collection of icons
     */
    void delete(Collection<AcctIcon> icons);

}
