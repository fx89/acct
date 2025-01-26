package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;
import com.desolatetimelines.acct.catalog.ws.model.IconsCountResponse;

import java.util.Collection;

/**
 * Defines operations that are supported by the Catalog service for icons
 */
public interface IconsEndpoint {

    /**
     * Creates a new icon with the details given in the request
     *
     * @param request the request
     * @return a container for the UUID of the newly created icon
     */
    IconUUIDResponse createIcon(IconCreateRequest request);

    /**
     * Returns a collection of the names of all the icon categories that are registered within the catalogS
     */
    Collection<String> getIconCategories();

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

}
