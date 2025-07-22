/**
 * Defines the body for an icon creation request
 */
export interface IconCreateRequest {

    /**
     * The name to be given to the icon (must be unique per category)
     */
    iconName : string

    /**
     * The name of the icon category the icon is to be mapped to (can be a new category name)
     */
    iconCategoryName : string

    /**
     * The mime type of the icon file
     */
    iconMimeType : string

    /**
     * The base64-encoded bytes of the icon's image file
     */
    iconBase64 : string

}