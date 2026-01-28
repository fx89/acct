export interface ReportProperties {
    reportName : string,
    reportDescription : string,
    reportType : ReportType,
    dataProviderInstanceUUIDs : string[],
    reportSQL : string,
    reportCategoryColumnName : string,
    reportSeries : ReportSeries[]
}

export interface ReportExtendedProperties {
    reportUUID? : string,
    reportProperties : ReportProperties
}

export enum ReportType {
    TABLE = 'TABLE',
    SERIES = 'SERIES',
    PIE = 'PIE'
}

export interface ReportSeries {
    reportColumnName : string,
    reportSeriesName : string,
    reportSeriesType : ReportSeriesType
}

export enum ReportSeriesType {
    AREA = 'AREA',
    LINE = 'LINE',
    COLUMN = 'COLUMN'
}

export function allSeriesTypes() : ReportSeriesType[] {
    return [
        ReportSeriesType.AREA,
        ReportSeriesType.LINE,
        ReportSeriesType.COLUMN
    ]
}