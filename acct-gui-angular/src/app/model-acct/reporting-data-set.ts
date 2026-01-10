
export function newReportingDataSet() : ReportingDataSet {
    return {
        recordCount: 0,
        columns: [],
        data: []
    }
}

/**
 * A reporting data set is the result of running a data provider, data provider instance or
 * report. It contains the extracted data, as well as the meta-data that describes the data.
 */
export interface ReportingDataSet {

    /**
     * The number of records in the data set.
     */
    recordCount : number,

    /**
     * The columns exposed by the data set, in the exact order in which they can be
     * found in the data array.
     */
    columns : ReportingDataSetColumn[],

    /**
     * The data array that contains the data exposed by the data set.
     */
    data : string[][]

}

/**
 * Column meta-data for use in reporting data sets.
 */
export interface ReportingDataSetColumn {

    /**
     * The name of the column
     */
    name : string,

    /**
     * The data type of the column
     */
    dataType : ReportingDataSetColumnDataType

}

/**
 * Possible types for a column contained in a reporting data set.
 */
export enum ReportingDataSetColumnDataType {
    TEXT,
    STRING,
    NUMERIC,
    TIMESTAMP
}