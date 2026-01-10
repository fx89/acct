import { Observable } from "rxjs";
import { DataProviderInstance } from "../../model-acct/data-provider-instance";
import { AcctDataProviderInstancesRepository } from "../data-provider-instances-repository";
import { DataProviderInstanceProperties } from "../../model-acct/data-provider-instance-properties";
import { DataProviderInstanceUUIDResponse } from "../../model-acct/data-provider-instance-uuid-response";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";

/**
 * Mock implementation for the data provider instances repository
 */
export class MockAcctDataProviderInstancesRepository extends AcctDataProviderInstancesRepository {

    /**
     * Retrieves a set of all the data provider instances available in the system
     */
    public findAllDataProviderInstances() : Observable<DataProviderInstance[]> {
        throw new Error("Method not implemented.")
    }

    /**
     * Returns an observable that produces the properties of the referenced data provider instnace
     * @param dataProviderInstanceUUID unique identifier for the data provider instance whose properties are being fetched
     */
    public override findDataProviderInstanceProperties(dataProviderInstanceUUID: string): Observable<DataProviderInstanceProperties> {
        throw new Error("Method not implemented.");
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
        throw new Error("Method not implemented.");
    }

    /**
     * Removes the referenced data provider instance from the data store.
     * 
     * @param dataProviderInstanceUUID Uniquely identifies the referenced data provider instance.
     */
    public override deleteDataProviderInstance(dataProviderInstanceUUID: string): Observable<void> {
        throw new Error("Method not implemented.");
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
        throw new Error("Method not implemented.");
    }

}