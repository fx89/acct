import { Observable } from "rxjs";
import { IconsCountResponse } from "../model-acct/icons-count-response";
import { IconProperties } from "../model-acct/icon-properties";
import { AcctPage } from "../model-acct/acct-page";
import { IconCreateRequest } from "../model-acct/icon-create-request";
import { IconUUIDResponse } from "../model-acct/icon-uuid-response";
import { IconDeleteRequest } from "../model-acct/icon-delete-request";
import { AcctPageRequest } from "../model-acct/page-request";
import { IconQueryParams } from "../model-acct/icon-query-params";

/**
 * Allows creating, reading, updating and deleting icons
 */
export abstract class AcctIconsRepository {

    /**
     * Returns an array of the names of all the registered icon categories
     */
    abstract findIconCategories() : Observable<string[]>

    /**
     * Creates a new icon category with the given name
     * @param iconCategoryName the given name
     */
    abstract createIconCategory(iconCategoryName:string) : Observable<void>

    /**
     * Deletes the icon category with the given name
     * @param iconCategoryName the given name
     */
    abstract deleteIconCategory(iconCategoryName:string) : Observable<void>

    /**
     * Creates a new icon with the details given in the request
     * 
     * @param request the request
     * @returns a container for the UUID of the newly created icon
     */
    abstract createIcon(request:IconCreateRequest) : Observable<IconUUIDResponse>

    /**
     * Returns a count of the icons that match the given name pattern and that belong to
     * the given category name. If a name pattern is not provided then the count includes
     * icons with any name. If the category name is not provided then the count includes
     * icons from all categories.
     * 
     * @param queryParams container for the query parameters of the icons count query
     */
    abstract countIcons(queryParams : IconQueryParams) : Observable<IconsCountResponse>

    /**
     * Returns a page, with the given page number and of the given page size, of the icons
     * that match the given name pattern and that belong to the given category name. If a
     * name pattern is not provided then the count includes icons with any name. If the
     * category name is not provided then the count includes icons from all categories.
     * 
     * @param pageRequest the page request object
     */
    abstract findIcons(pageRequest: AcctPageRequest<IconQueryParams>) : Observable<AcctPage<IconProperties>>

    /**
     * Returns the base64-encoded bytes of the icon with the given icon UUID
     * 
     * @param iconUUID the given icon UUID
     */
    abstract loadIconBytesBase64(iconUUID:string) : Observable<string>

    /**
     * Deletes the icons identified by the UUIDs in the given collection of icon UUIDs
     * 
     * @param request container for the given collection of icon UUIDs
     */
    abstract deleteIcons(request:IconDeleteRequest) : Observable<void>

}