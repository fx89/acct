import { Observable } from "rxjs";
import { AcctDataProvidersRepository } from "../data-providers-repository";
import { DataProvider } from "../../model-acct/data-provider";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";

/**
 * Mock implementation of the AcctDataProvidersRepository
 */
export class MockAcctDataProvidersRepository extends AcctDataProvidersRepository {

    /**
     * Retrieves a set of all the data providers available in the system
     */
    public override findAllDataProviders(): Observable<DataProvider[]> {
        throw new Error("Method not implemented.");
    }

    /**
     * Retrieves the data set produced by the referenced data provider for the given
     * instance properties and runime parameters
     * 
     * @param dataProviderUUID Unique identifier for the referenced data provider
     * @param instanceProperties Optional properties to be used when initializing the data provider instance
     * @param runtimeParameters Optional parameters to be used when running the data provider instance
     */
    public override fetchDataDataSet(
        dataProviderUUID: string,
        instanceProperties: Map<string, string>,
        runtimeParameters: Map<string, string>
    ) : Observable<ReportingDataSet> {
        throw new Error("Method not implemented.");
    }
    
}