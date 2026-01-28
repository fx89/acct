/**
 * Describes the readable properties of a data provider in the ACCT ecosystem
 */
export interface DataProvider {
    /**
     * Unique identifier for the data provider within the ACCT ecosystem
     */
    uuid? : string

    /**
     * Human-readable name of the data provider
     */
    humanReadableName : string

    /**
     * Human-readable description of the data exposed by the data provider
     */
    description : string

    /**
     * Mandatory properties that a data provier instance must supply if the data provider is to run correctly
     */
    instanceProperties : DataProviderInstanceProperty[]

    /**
     * Runtime parameters supported by the data provider
     */
    parameters : DataProviderParameter[]
}



/**
 * Describes an instance property of a data provider
 */
export interface DataProviderInstanceProperty {
    /**
     * The name of the instance property is unique per data provider
     */
    name : string

    /**
     * The data type that the property is expected to have
     */
    dataType : DataProviderPropertyDataType
}



/**
 * Describes a runtime parameter of a data provider
 */
export interface DataProviderParameter {
    /**
     * The name of the runtime parameter is unique per data provider
     */
    name : string

    /**
     * The data type that the parameter is expected to have
     */
    dataType : DataProviderPropertyDataType

    /**
     * Set to true if the data provider cannot run if this parameter is not provided
     */
    mandatory : boolean
}



/**
 * Enumerates the data types that a data provider instance property or data provider parameter
 * can be expected to have
 */
export enum DataProviderPropertyDataType {
    STRING = 'STRING',
    NUMERIC = 'NUMERIC',
    DATETIME = 'DATETIME',
    BOOLEAN = 'BOOLEAN'
}

/**
 * Returns an array of all possible data provider property data types
 */
export function allDataProviderPropertyDataTypes() : DataProviderPropertyDataType[] {
    return [
        DataProviderPropertyDataType.STRING,
        DataProviderPropertyDataType.NUMERIC,
        DataProviderPropertyDataType.DATETIME,
        DataProviderPropertyDataType.BOOLEAN
    ]
}