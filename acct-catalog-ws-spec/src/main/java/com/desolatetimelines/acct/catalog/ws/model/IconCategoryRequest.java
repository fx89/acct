package com.desolatetimelines.acct.catalog.ws.model;

import jakarta.validation.constraints.NotNull;

/**
 * Container for the modifiable properties of an icon category
 *
 * @param iconCategoryName the display name that uniquely identifies the icon category in the catalog
 */
public record IconCategoryRequest(
    @NotNull
    String iconCategoryName
) {

}
