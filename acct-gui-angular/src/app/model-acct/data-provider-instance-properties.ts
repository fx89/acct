import { DataProviderPropertyDataType } from "./data-provider"

/**
 * Describes the readable and modifiable properties of a data provider instance in the ACCT ecosystem
 */
export interface DataProviderInstanceProperties {
    /**
     * The human-readable name of the data provider instance. Identifies the data provider instance in the GUI.
     */
    instanceName : string

    /**
     * Property that is carried over from DataProviderInstance
     */
    dataProviderInstanceName? : string

    /**
     * The UUID of the data provider whose instance this is.
     */
    dataProviderUUID : string

    /**
     * A set of properties with constant values o be given to the data provider instance upon initialization.
     * These are stated by the data provider and serve the purpose of configuration variables.
     */
    instanceProperties : DataProviderInstanceProperty[]

    /**
     * A set of runtime parameters that are not defined by the data provider itself, but are required for the
     * custom functionality added through one or more instance properties (i.e. a SQL query). If the data
     * provider already defines this runtime parameter, then it does not need to exist here. Unlike instance
     * properties, which are constant throughout the life span of the data provider instance, the runtime
     * parameters change their values with each invocation of the data provider instance. The values of the
     * runtime parameters are provided by users via the GUI.
     */
    runtimeParameters : DataProviderInstanceRuntimeParameter[]
}

/**
 * Container for the properties of a data provider instance property. Used for both read and write purposes.
 */
export interface DataProviderInstanceProperty {
    /**
     * Uniquely identifies the instance property within the context of the data
     * provider instance. Must match the name of one of the instance properties
     * defined by the data provider.
     */
    propertyName : string

    /**
     * The value set by the user for the instance property. Must be parsable into
     * the data type defined by the data provider.
     */
    propertyValue : string
}

export interface DataProviderInstanceRuntimeParameter {
    parameterName : string
    parameterDefaultValue : string
    parameterDataType : DataProviderPropertyDataType
    mandatory : boolean
}