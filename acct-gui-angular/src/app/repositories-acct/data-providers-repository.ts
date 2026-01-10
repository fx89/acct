import { Observable } from "rxjs";
import { DataProvider } from "../model-acct/data-provider";
import { ReportingDataSet } from "../model-acct/reporting-data-set";

/**
 * Specification for the data providers repository
 */
export abstract class AcctDataProvidersRepository {

    /**
     * Retrieves a set of all the data providers available in the system
     */
    public abstract findAllDataProviders() : Observable<DataProvider[]>

    /**
     * Retrieves the data set produced by the referenced data provider for the given
     * instance properties and runime parameters
     * 
     * @param dataProviderUUID Unique identifier for the referenced data provider
     * @param instanceProperties Optional properties to be used when initializing the data provider instance
     * @param runtimeParameters Optional parameters to be used when running the data provider instance
     */
    public abstract fetchDataDataSet(
        dataProviderUUID:string,
        instanceProperties:Map<string,string>,
        runtimeParameters:Map<string,string>
    ) : Observable<ReportingDataSet>

}