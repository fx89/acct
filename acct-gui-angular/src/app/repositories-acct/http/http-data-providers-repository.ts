import { identity, Observable } from "rxjs";
import { AcctDataProvidersRepository } from "../data-providers-repository";
import { DataProvider } from "../../model-acct/data-provider";
import { createBodyProcessingHttpClientWrapperHandlers, HttpConnector } from "../../services-reusable/http-connectors.service";
import { ReportingDataSet } from "../../model-acct/reporting-data-set";

/**
 * Implementation of the AcctDataProvidersRepository that connects to the back-end
 * services to provide functionality
 */
export class HttpAcctDataProvidersRepository extends AcctDataProvidersRepository {

    constructor(private httpConnector : HttpConnector) {
        super()
    }

    /**
     * Retrieves a set of all the data providers available in the system
     */
    public override findAllDataProviders(): Observable<DataProvider[]> {
        return new Observable<DataProvider[]>(subscriber => {
            this.httpConnector.get(
                {
                    url: "/dataProviders"
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "No data providers were found."
                )
            )
        })
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
    ) : Observable<ReportingDataSet>
    {
        return new Observable<ReportingDataSet>(subscriber => {
            this.httpConnector.post(
                {
                    url: "/dataProviders/dataSet?dataProviderUUID=" + dataProviderUUID,
                    data: {
                        body: {
                            instanceProperties: Object.fromEntries(instanceProperties.entries()),
                            runtimeParameters: Object.fromEntries(runtimeParameters.entries())
                        }
                    }
                },
                createBodyProcessingHttpClientWrapperHandlers(
                    subscriber,
                    identity,
                    "No data providers were found."
                )
            )
        })
    }
    
}