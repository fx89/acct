import { Observable } from "rxjs";
import { AcctPage } from "../../model-acct/acct-page";
import { IconCreateRequest } from "../../model-acct/icon-create-request";
import { IconProperties } from "../../model-acct/icon-properties";
import { IconUUIDResponse } from "../../model-acct/icon-uuid-response";
import { IconsCountResponse } from "../../model-acct/icons-count-response";
import { IconQueryParams } from "../../model-acct/icon-query-params";
import { AcctPageRequest } from "../../model-acct/page-request";
import { AcctIconsRepository } from "../icons-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";

export class HttpAcctIconsRepository extends AcctIconsRepository {

    constructor(
        private httpConnector : HttpConnector
    ) {
        super()
    }

    override findIconCategories(): Observable<string[]> {
        return new Observable<string[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/icons/iconCategories"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:string[]) => responseBody,
                    "Icon categories not found."
                )
            )
        })
    }

    override createIconCategory(iconCategoryName:string) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/icons/iconCategories",
                    data: {
                        body: {
                            iconCategoryName: iconCategoryName
                        }
                    }
                },
                {
                    responseHandler: response => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: error => {
                        subscriber.error(error)
                    }
                }
            )
        })
    }

    override deleteIconCategory(iconCategoryName:string) : Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/icons/iconCategories",
                    data: {
                        params: {
                            iconCategoryName: iconCategoryName
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: error => subscriber.error(error)
                }
            )
        })
    }

    override createIcon(request: IconCreateRequest): Observable<IconUUIDResponse> {
        return new Observable<IconUUIDResponse>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/icons",
                    data: {
                        body: request
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:IconUUIDResponse) => responseBody,
                    "Icon not created."
                )
            )
        })
    }

    override countIcons(queryParams : IconQueryParams): Observable<IconsCountResponse> {
        const params : Record<string,string> = {}

        if (queryParams.iconNamePattern) {
            params["iconNamePattern"] = queryParams.iconNamePattern
        }

        if (queryParams.iconCategoryName) {
            params["iconCategoryName"] = queryParams.iconCategoryName
        }

        return new Observable<IconsCountResponse>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/icons/count",
                    data: {
                        params: params
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:IconsCountResponse) => responseBody,
                    "Icons not found."
                )
            )
        })
    }

    override findIcons(pageRequest: AcctPageRequest<IconQueryParams>): Observable<AcctPage<IconProperties>> {
        const params : Record<string,string|number> = {
            pageNumber : pageRequest.pageNumber,
            pageSize : pageRequest.pageSize
        }

        if (pageRequest.queryParameters.iconNamePattern) {
            params["iconNamePattern"] = pageRequest.queryParameters.iconNamePattern
        }

        if (pageRequest.queryParameters.iconCategoryName) {
            params["iconCategoryName"] = pageRequest.queryParameters.iconCategoryName
        }

        return new Observable<AcctPage<IconProperties>>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/icons",
                    data: {
                        params: params
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:AcctPage<IconProperties>) => responseBody,
                    "Icons not found."
                )
            )
        })
    }

    override loadIconBytesBase64(iconUUID: string): Observable<string> {
        return new Observable<string>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/icons/icon",
                    data: {
                        params: {
                            iconUUID: iconUUID
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:string) => responseBody,
                    "Icon data not found."
                ),
                'text'
            )
        })
    }
    
    override deleteIcons(iconUUIDs : string[]): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/icons",
                    data: {
                        params: {
                            iconUUIDs: iconUUIDs
                        }
                    }
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        })
    }

}