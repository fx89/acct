import { identity, Observable } from "rxjs";
import { DataProviderInstance } from "../../model-acct/data-provider-instance";
import { AcctDataProviderInstancesRepository } from "../data-provider-instances-repository";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { DataProviderInstanceProperties } from "../../model-acct/data-provider-instance-properties";
import { DataProviderInstanceUUIDResponse } from "../../model-acct/data-provider-instance-uuid-response";
import { DataProviderPropertyDataType } from "../../model-acct/data-provider";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";

/**
 * Implementation of the AcctDataProviderInstancesRepository that connects to the back-end
 * services to provide functionality
 */
export class HttpAcctDataProviderInstancesRepository extends AcctDataProviderInstancesRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    /**
     * Retrieves a set of all the data provider instances available in the system
     */
    public findAllDataProviderInstances() : Observable<DataProviderInstance[]> {
        return new Observable<DataProviderInstance[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/dataProviderInstances"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "No data provider instances were found."
                )
            )
        })
    }

    /**
     * Returns an observable that produces the properties of the referenced data provider instnace
     * @param dataProviderInstanceUUID unique identifier for the data provider instance whose properties are being fetched
     */
    public override findDataProviderInstanceProperties(dataProviderInstanceUUID: string): Observable<DataProviderInstanceProperties> {
        return new Observable<DataProviderInstanceProperties>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/dataProviderInstances/details?dataProviderInstanceUUID=" + dataProviderInstanceUUID
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (response:DataProviderInstanceProperties) => ({
                        instanceName : response.instanceName,
                        dataProviderUUID : response.dataProviderUUID,
                        instanceProperties : response.instanceProperties,
                        runtimeParameters : response.runtimeParameters.map(param => ({
                            parameterName : param.parameterName,
                            parameterDefaultValue : param.parameterDefaultValue,
                            parameterDataType : this.fixDataProviderPropertyDataType(param.parameterDataType),
                            mandatory : param.mandatory
                        }))
                    }),
                    "Data provider instance properties not found."
                )
            )
        })
    }

    /**
     * The parameter type comes in as a string from the back-end. TS thinks it's an enum, but it's not.
     */
    private fixDataProviderPropertyDataType(type : DataProviderPropertyDataType) : DataProviderPropertyDataType {
        // Make it think it's a string
        const strType = "" + type

        // Get the right enum value for the string
        if (strType == "BOOLEAN") {
            return DataProviderPropertyDataType.BOOLEAN
        }

        if (strType == "DATETIME") {
            return DataProviderPropertyDataType.DATETIME
        }

        if (strType == "NUMERIC") {
            return DataProviderPropertyDataType.NUMERIC
        }

        return DataProviderPropertyDataType.STRING
    }

    /**
     * Persists the given data provider instance. If a data provider instance UUID was provided, then
     * the given data provider instance overrides the one referenced by the UUID (if it exists).
     * Returns a container for the UUID of the persisted data provider instance, which is particularly
     * useful in case the data provider instance being persisted is new.
     * 
     * @param dataProviderInstanceUUID 
     * @param dataProviderInstance 
     */
    public override saveDataProviderInstance(
        dataProviderInstance: DataProviderInstanceProperties,
        dataProviderInstanceUUID?: string
        
    ): Observable<DataProviderInstanceUUIDResponse>
    {
        return new Observable<DataProviderInstanceUUIDResponse>(subscriber => {
            // Create parameters object
            const params : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>> = {}

            // If the data provider instance has an UUID, then add it to the parameters object
            if (dataProviderInstanceUUID) {
                params["dataProviderInstanceUUID"] = dataProviderInstanceUUID
            }

            this.httpConnector.post(
                {
                    url: "/dataProviderInstances",
                    data: {
                        params: params,
                        body: {
                            instanceName       : dataProviderInstance.instanceName,
                            dataProviderUUID   : dataProviderInstance.dataProviderUUID,
                            instanceProperties : dataProviderInstance.instanceProperties ?? [],
                            runtimeParameters  : dataProviderInstance.runtimeParameters ?? []
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    (responseBody) => responseBody,
                    "Data provider instance not saved."
                )
            )
        })
    }

    /**
     * Removes the referenced data provider instance from the data store.
     * 
     * @param dataProviderInstanceUUID Uniquely identifies the referenced data provider instance.
     */
    public override deleteDataProviderInstance(dataProviderInstanceUUID: string): Observable<void> {
        return new Observable<void>(subscriber => {
            this.httpConnector.delete(
                {
                    url: "/dataProviderInstances",
                    data: {
                        params: {
                            dataProviderInstanceUUID: dataProviderInstanceUUID
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

    /**
     * Runs the referenced data provider instance with the given runtime parameters (if any) and
     * returns a data set that contains the results of the run.
     * 
     * @param dataProviderInstanceUUID Unique identifier for the referenced data provider instance.
     * @param runtimeParameters Optional runtime parameters for the run.
     */
    public override fetchDataProviderInstanceDataSet(
        dataProviderInstanceUUID: string,
        runtimeParameters?: Map<string, string>
    ): Observable<ReportingDataSet>
    {
        // Resolve the parameters
        const params = Object.fromEntries((runtimeParameters ?? new Map()).entries())

        // Run the data provider instance and fetch the data
        return new Observable<ReportingDataSet>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/dataProviderInstances/dataSet?dataProviderInstanceUUID=" + dataProviderInstanceUUID,
                    data: {
                        body: params
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "No data provider instances were found."
                )
            )
        })
    }

}