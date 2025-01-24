package com.desolatetimelines.acct.catalog.ws.endpoint;

import com.desolatetimelines.acct.catalog.ws.model.IconCreateRequest;
import com.desolatetimelines.acct.catalog.ws.model.IconUUIDResponse;

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

}
