import { Observable } from "rxjs";
import { AcctPage } from "../../model-acct/acct-page";
import { DataProviderInstanceRuntimeParameter } from "../../model-acct/data-provider-instance-properties";
import { ReportParameter } from "../../model-acct/report-parameter";
import { ReportExtendedProperties, ReportProperties } from "../../model-acct/report-properties";
import { ReportUUIDResponse } from "../../model-acct/report-uuid-response";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";
import { AcctReportsRepository } from "../reports-repository";


export class MockAcctReportsRepository extends AcctReportsRepository {

    public override findSortedPageOfUserAccessibleReports(
        pageNumber: number,
        pageSize: number
    ): Observable<AcctPage<ReportExtendedProperties>>
    {
        throw new Error("Method not implemented.");
    }

    public override saveReport(
        report: ReportProperties,
        reportUUID?: string
    ): Observable<ReportUUIDResponse>
    {
        throw new Error("Method not implemented.");
    }

    public override deleteReport(reportUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
    }

    public override getReportRuntimeParameters(
        reportUUID: string
    ): Observable<DataProviderInstanceRuntimeParameter[]>
    {
        throw new Error("Method not implemented.");
    }

    public override getReportDataWithRuntimeParameters(
        reportUUID: string,
        parameters: ReportParameter[]
    ): Observable<ReportingDataSet>
    {
        throw new Error("Method not implemented.");
    }
    
}