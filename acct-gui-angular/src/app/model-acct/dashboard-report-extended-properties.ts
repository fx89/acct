import { ReportSeries, ReportType } from "./report-properties"

export interface DashboardReportProperties {
    reportUUID? : string,
    rowNumber : number,
    columnNumber : number,
    containerName : string,
    containerWidthPx : number,
    containerHeightPx : number,
    filters : DashboardReportFilterProperties[]
}

export interface DashboardReportExtendedProperties extends DashboardReportProperties {
    reportName : string,
    reportDescription : string,
    reportType : ReportType,
    reportCategoryColumnName : string,
    reportSeries : ReportSeries[]
}

export interface DashboardReportFilterProperties {
    filterName : string,
    reportColumnName : string
}