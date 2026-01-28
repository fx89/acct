import { Observable } from "rxjs";
import { AcctPage } from "../../model-acct/acct-page";
import { ReportExtendedProperties, ReportProperties } from "../../model-acct/report-properties";
import { AcctReportsRepository } from "../reports-repository";
import { ReportUUIDResponse } from "../../model-acct/report-uuid-response";
import { DataProviderInstanceRuntimeParameter } from "../../model-acct/data-provider-instance-properties";
import { ReportParameter } from "../../model-acct/report-parameter";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { HttpClientWrapperRequestData } from "../../services-reusable/http-client-wrapper.service";

export class HttpAcctReportsRepository extends AcctReportsRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    public override findSortedPageOfUserAccessibleReports(
        pageNumber: number,
        pageSize: number
    ): Observable<AcctPage<ReportExtendedProperties>>
    {
        return new Observable<AcctPage<ReportExtendedProperties>>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/reports",
                    data: {
                        params: {
                            pageNumber: pageNumber,
                            pageSize: pageSize
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:AcctPage<ReportExtendedProperties>) => responseBody,
                    "Unable to fetch reports."
                )
            )
        })
    }

    public override saveReport(
        report: ReportProperties,
        reportUUID?: string
    ): Observable<ReportUUIDResponse>
    {
        return new Observable<ReportUUIDResponse>(subscriber =>
            this.httpConnector.post(
                {
                    url: "/reports" + (reportUUID ? `?reportUUID=${reportUUID}` : ""),
                    data: {
                        body: report
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody:ReportUUIDResponse) => responseBody,
                    "Unable to save report."
                )
            )
        )
    }

    public override deleteReport(reportUUID:string) : Observable<void> {
        return new Observable<void>(subscriber =>
            this.httpConnector.delete(
                {
                    url: `/reports?reportUUID=${reportUUID}`
                },
                {
                    responseHandler: () => {
                        subscriber.next()
                        subscriber.complete()
                    },
                    errorHandler: err => subscriber.error(err)
                }
            )
        )
    }

    public override getReportRuntimeParameters(
        reportUUID: string
    ): Observable<DataProviderInstanceRuntimeParameter[]>
    {
        return new Observable<DataProviderInstanceRuntimeParameter[]>(subscriber =>
            this.httpConnector.get(
                {
                    url: `/reports/parameters?reportUUID=${reportUUID}`
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody: DataProviderInstanceRuntimeParameter[]) => responseBody,
                    "Unable to fetch report runtime parameters."
                )
            )
        )
    }

    public override getReportDataWithRuntimeParameters(
        reportUUID: string,
        parameters: ReportParameter[]
    ): Observable<ReportingDataSet>
    {
        const data : HttpClientWrapperRequestData<ReportParameter[]> = {
            body: parameters
        }

        return new Observable<ReportingDataSet>(subscriber =>
            this.httpConnector.post(
                {
                    url: `/reports/data?reportUUID=${reportUUID}`,
                    data: <HttpClientWrapperRequestData<undefined>> <unknown> data
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody: ReportingDataSet) => responseBody,
                    "Unable to fetch report data."
                )
            )
        )
    }
    
}