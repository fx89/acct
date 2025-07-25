import { Observable } from "rxjs";
import { AcctPage } from "../../model-acct/acct-page";
import { IconCreateRequest } from "../../model-acct/icon-create-request";
import { IconDeleteRequest } from "../../model-acct/icon-delete-request";
import { IconProperties } from "../../model-acct/icon-properties";
import { IconUUIDResponse } from "../../model-acct/icon-uuid-response";
import { IconsCountResponse } from "../../model-acct/icons-count-response";
import { IconQueryParams } from "../../model-acct/icon-query-params";
import { AcctPageRequest } from "../../model-acct/page-request";
import { AcctIconsRepository } from "../icons-repository";

export class MockAcctIconsRepository extends AcctIconsRepository {

    override findIconCategories(): Observable<string[]> {
        throw new Error("Method not implemented.");
    }

    override createIconCategory(iconCategoryName:string) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override deleteIconCategory(iconCategoryName:string) : Observable<void> {
        throw new Error("Method not implemented.");
    }

    override createIcon(request: IconCreateRequest): Observable<IconUUIDResponse> {
        throw new Error("Method not implemented.");
    }

    override countIcons(queryParams : IconQueryParams): Observable<IconsCountResponse> {
        throw new Error("Method not implemented.");
    }

    override findIcons(pageRequest: AcctPageRequest<IconQueryParams>): Observable<AcctPage<IconProperties>> {
        throw new Error("Method not implemented.");
    }

    override loadIconBytesBase64(iconUUID: string): Observable<string> {
        throw new Error("Method not implemented.");
    }
    
    override deleteIcons(request: IconDeleteRequest): Observable<void> {
        throw new Error("Method not implemented.");
    }

}