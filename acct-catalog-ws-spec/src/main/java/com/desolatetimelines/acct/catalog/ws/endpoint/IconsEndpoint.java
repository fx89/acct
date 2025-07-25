package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.*;
import com.desolatetimelines.acct.common.ws.model.AcctPage;
import com.desolatetimelines.acct.common.ws.model.AcctStatusResponse;

import java.util.Collection;

/**
 * Defines operations that are supported by the Catalog service for icons
 */
public interface IconsEndpoint {

    /**
     * Returns a collection of the names of all the icon categories that are registered within the catalog
     * service
     */
    Collection<String> getIconCategories();

    /**
     * Adds a new icon category to the catalog.
     *
     * @param iconCategoryRequest request object that contains the name of the icon category to be created
     */
    AcctStatusResponse createIconCategory(IconCategoryRequest iconCategoryRequest);

    /**
     * Deletes the referenced icon category from the catalog
     *
     * @param iconCategoryName The name of the icon category to be deleted
     */
    AcctStatusResponse deleteIconCategory(String iconCategoryName);

    /**
     * Creates a new icon with the details given in the request
     *
     * @param request the request
     * @return a container for the UUID of the newly created icon
     */
    IconUUIDResponse createIcon(IconCreateRequest request);

    /**
     * Returns a count of the icons that match the given name pattern and that belong to
     * the given category name. If a name pattern is not provided then the count includes
     * icons with any name. If the category name is not provided then the count includes
     * icons from all categories.
     *
     * @param iconNamePattern  the given name pattern - optional - must be at least 3 characters long
     * @param iconCategoryName the given category name - optional
     */
    IconsCountResponse getIconsCount(String iconNamePattern, String iconCategoryName);

    /**
     * Returns a page, with the given page number and of the given page size, of the icons
     * that match the given name pattern and that belong to the given category name. If a
     * name pattern is not provided then the count includes icons with any name. If the
     * category name is not provided then the count includes icons from all categories.
     *
     * @param iconNamePattern  the given name pattern - optional - must be at least 3 characters long
     * @param iconCategoryName the given category name - optional
     * @param pageNumber       the given page number
     * @param pageSize         the given page size
     */
    AcctPage<IconProperties> getIcons(String iconNamePattern, String iconCategoryName, int pageNumber, int pageSize);

    /**
     * Returns the base64-encoded bytes of the icon with the given icon UUID
     *
     * @param iconUUID the given icon UUID
     */
    String getIconBytesBase64(String iconUUID);

    /**
     * Deletes the icons identified by the UUIDs in the given collection of icon UUIDs
     *
     * @param iconUUIDs the given collection of icon UUIDs
     */
    AcctStatusResponse deleteIcons(Collection<String> iconUUIDs);

}
