import { Observable } from "rxjs";
import { AcctPage } from "../model-acct/acct-page";
import { ReportExtendedProperties, ReportProperties } from "../model-acct/report-properties";
import { ReportUUIDResponse } from "../model-acct/report-uuid-response";
import { DataProviderInstanceRuntimeParameter } from "../model-acct/data-provider-instance-properties";
import { ReportParameter } from "../model-acct/report-parameter";
import { ReportingDataSet } from "../model-acct/reporting-data-set";

/**
 * Specification for the reports repository
 */
export abstract class AcctReportsRepository {

    /**
     * Retrieves a page of {@link ReportExtendedProperties reports} that are accessible to the
     * current user either directly or via a group that the user is part of.
     * 
     * @param pageNumber The number of the page to be retrieved.
     * @param pageSize The number of elements to be contained within the page.
     */
    public abstract findSortedPageOfUserAccessibleReports(
        pageNumber : number,
        pageSize : number
    ) : Observable<AcctPage<ReportExtendedProperties>>

    /**
     * Creates or updates a report in the ACCT ecosystem.
     * 
     * @param report The report properties define the report behavior and appearance.
     * @param reportUUID Unique identifier of the report. If missing, a new report is created.
     */
    public abstract saveReport(
        report : ReportProperties,
        reportUUID? : string
    ) : Observable<ReportUUIDResponse>

    /**
     * Deletes the referenced report from the ACCT ecosystem.
     * 
     * @param reportUUID Uniquely identifes the report to be deleted.
     */
    public abstract deleteReport(reportUUID:string) : Observable<void>

    /**
     * Retrieves a set of all the runtime parameters accepted by the referenced report.
     * 
     * @param reportUUID Uniquely identifies the report.
     */
    public abstract getReportRuntimeParameters(
        reportUUID : string
    ) : Observable<DataProviderInstanceRuntimeParameter[]>

    /**
     * Runs the referenced report and returns the data set resulted from running the report.
     * 
     * @param reportUUID Uniquely identifies the report to run.
     * @param parameters Contains the runtime parameters for the report. At least the mandatory parameters need to be provided.
     */
    public abstract getReportDataWithRuntimeParameters(
        reportUUID : string,
        parameters: ReportParameter[]
    ) : Observable<ReportingDataSet>

}