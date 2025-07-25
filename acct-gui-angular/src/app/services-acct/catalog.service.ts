import { Injectable } from '@angular/core';
import { AcctIconsRepository } from '../repositories-acct/icons-repository';
import { IconCreateRequest } from '../model-acct/icon-create-request';
import { Observable } from 'rxjs';
import { IconUUIDResponse } from '../model-acct/icon-uuid-response';
import { IconQueryParams } from '../model-acct/icon-query-params';
import { IconsCountResponse } from '../model-acct/icons-count-response';
import { AcctPageRequest } from '../model-acct/page-request';
import { AcctPage } from '../model-acct/acct-page';
import { IconProperties } from '../model-acct/icon-properties';

/**
 * Provides access to functions of the catalog service
 */
@Injectable({
  providedIn: 'root'
})
export class CatalogService {
  
  constructor(
    private iconsRepository : AcctIconsRepository
  ) { }

  /**
   * Creates a new icon with the details given in the request
   * 
   * @param request the request
   * @returns a container for the UUID of the newly created icon
   */
  public createIcon(request: IconCreateRequest): Observable<IconUUIDResponse> {
    return this.iconsRepository.createIcon(request)
  }

  public createIconCategory(iconCategoryName:string) : Observable<void> {
    return this.iconsRepository.createIconCategory(iconCategoryName)
  }

  /**
   * Returns an array of the names of all the registered icon categories
   */
  public findIconCategories(): Observable<string[]> {
    return this.iconsRepository.findIconCategories()
  }

  /**
   * Deletes the icon category with the given name
   * @param iconCategoryName the given name
   */
  public deleteIconCategory(iconCategoryName:string) : Observable<void> {
    return this.iconsRepository.deleteIconCategory(iconCategoryName)
  }

  /**
   * Returns a count of the icons that match the given name pattern and that belong to
   * the given category name. If a name pattern is not provided then the count includes
   * icons with any name. If the category name is not provided then the count includes
   * icons from all categories.
   * 
   * @param queryParams container for the query parameters of the icons count query
   */
  public countIcons(queryParams : IconQueryParams): Observable<IconsCountResponse> {
    return this.iconsRepository.countIcons(queryParams)
  }

  /**
   * Returns a page, with the given page number and of the given page size, of the icons
   * that match the given name pattern and that belong to the given category name. If a
   * name pattern is not provided then the count includes icons with any name. If the
   * category name is not provided then the count includes icons from all categories.
   * 
   * @param pageRequest the page request object
   */
  public findIcons(pageRequest: AcctPageRequest<IconQueryParams>): Observable<AcctPage<IconProperties>> {
    return this.iconsRepository.findIcons(pageRequest)
  }

  /**
   * Returns the base64-encoded bytes of the icon with the given icon UUID
   * 
   * @param iconUUID the given icon UUID
   */
  public loadIconBytesBase64(iconUUID: string): Observable<string> {
    return this.iconsRepository.loadIconBytesBase64(iconUUID)
  }

  /**
   * Deletes the icons identified by the UUIDs in the given collection of icon UUIDs
   * 
   * @param iconUUIDs the given collection of icon UUIDs
   */
  public deleteIcons(iconUUIDs : string[]): Observable<void> {
    return this.iconsRepository.deleteIcons(iconUUIDs)
  }

}
