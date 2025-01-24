package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Defines the body for an icon creation request
 *
 * @param iconName         The name to be given to the icon (must be unique per category)
 * @param iconCategoryName The name of the icon category the icon is to be mapped to (can be a new category name)
 * @param iconBase64       The base64-encoded bytes of the icon's image file
 */
public record IconCreateRequest(
    String iconName,
    String iconCategoryName,
    String iconBase64
) {
}
