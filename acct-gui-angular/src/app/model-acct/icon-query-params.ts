/**
 * Contianer for the query parameters available when query-ing for an icons page
 */
export interface IconQueryParams {

    /**
     * The name pattern of the icon. Must be at least 3 letters long.
     */
    iconNamePattern : string | undefined

    /**
     * The name of the icons category by which to filter the results.
     */
    iconCategoryName : string | undefined

}