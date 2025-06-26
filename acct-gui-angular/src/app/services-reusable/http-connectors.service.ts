import { Inject, Injectable } from '@angular/core';
import { HttpClientWrapperHandlers, HttpClientWrapperMethodlessRequest, HttpClientWrapperRequest, HttpClientWrapperResponse, HttpClientWrapperService } from './http-client-wrapper.service';
import { NumericSequence } from '../utils-reusalbe/numeric-sequence';
import { Subscriber } from 'rxjs';

/**
 * Specification for the user-defined piece of code that resolves the base URL, or load-balanced
 * base URLs, for a givne HTTP connector.
 */
export interface HttpConnectorBaseURLsResolver {
  /**
   * Returns the base URL, or the list of load-balanced base URLs, that can be used for accessing
   * a given service.
   */
  resolveBaseURLs() : string | string[]
}

/**
 * Specifies the signature of a function that modifies or replaces the request sent to an HttpConnector
 * before the request is executed. This helps with adding service-wide headers.
 */
export type HttpConnectorPreRequestHook<REQ> = (
  (request:HttpClientWrapperMethodlessRequest<REQ>) => HttpClientWrapperMethodlessRequest<REQ>
)

/**
 * Handles HTTP requests to a given service that is represented by one or more alternative base URLs.
 */
export class HttpConnector {

  private readonly baseURLs   : string[] = []
  private urlIndexingSequence : NumericSequence = new NumericSequence(0,1)

  constructor(
    private readonly serviceName       : string,
    private readonly servicePath       : string,
    private readonly httpClientWrapper : HttpClientWrapperService,
    private readonly baseURLsResolver  : HttpConnectorBaseURLsResolver,
    private readonly preRequestHooks   : HttpConnectorPreRequestHook<any>[]
  ) {

  }

  /**
   * Send the given request and handle the response, or the request progress, using
   * the given handlers (if any)
   * 
   * @param request  the given request
   * @param handlers the given handlers
   */
  public request<REQ,RES,ERR>(
    request   : HttpClientWrapperRequest<REQ>,
    handlers? : HttpClientWrapperHandlers<RES,ERR>
  ) : void
  {
    // Process the pre-request hooks
    request = this.processPreRequestHooks(request)

    // Execute the request
    this.httpClientWrapper.request(
      this.updateHttpClientWrapperRequestUrl(request, this.resolveBaseUrl(), this.servicePath),
      handlers
    )
  }

  /**
   * Send a GET request with the given request data and handle the response, or the request progress,
   * using the given handlers (if any)
   * 
   * @param request  the given request data
   * @param handlers the given handlers
   */
  public get<RES,ERR>(
    request   : HttpClientWrapperMethodlessRequest<undefined>,
    handlers? : HttpClientWrapperHandlers<RES,ERR>
  ) : void
  {
    // Process the pre-request hooks
    request = this.processPreRequestHooks(request)

    // Execute the request
    this.httpClientWrapper.get(
      this.updateHttpClientWrapperMethodlessRequestUrl(request, this.resolveBaseUrl(), this.servicePath),
      handlers
    )
  }

  /**
   * Send a POST request with the given request data and handle the response, or the request progress,
   * using the given handlers (if any)
   * 
   * @param request  the given request data
   * @param handlers the given handlers
   */
  public post<REQ,RES,ERR>(
    request   : HttpClientWrapperMethodlessRequest<REQ>,
    handlers? : HttpClientWrapperHandlers<RES,ERR>
  ) : void
  {
    // Process the pre-request hooks
    request = this.processPreRequestHooks(request)

    // Execute the request
    this.httpClientWrapper.post(
      this.updateHttpClientWrapperMethodlessRequestUrl(request, this.resolveBaseUrl(), this.servicePath),
      handlers
    )
  }

  /**
   * Send a PUT request with the given request data and handle the response, or the request progress,
   * using the given handlers (if any)
   * 
   * @param request  the given request data
   * @param handlers the given handlers
   */
  public put<REQ,RES,ERR>(
    request   : HttpClientWrapperMethodlessRequest<REQ>,
    handlers? : HttpClientWrapperHandlers<RES,ERR>
  ) : void
  {
    // Process the pre-request hooks
    request = this.processPreRequestHooks(request)

    // Execute the request
    this.httpClientWrapper.put(
      this.updateHttpClientWrapperMethodlessRequestUrl(request, this.resolveBaseUrl(), this.servicePath),
      handlers
    )
  }

  /**
   * Send a DELETE request with the given request data and handle the response, or the request progress,
   * using the given handlers (if any)
   * 
   * @param request  the given request data
   * @param handlers the given handlers
   */
  public delete<RES,ERR>(
    request   : HttpClientWrapperMethodlessRequest<undefined>,
    handlers? : HttpClientWrapperHandlers<RES,ERR>
  ) : void
  {
    // Process the pre-request hooks
    request = this.processPreRequestHooks(request)

    // Execute the request
    this.httpClientWrapper.delete(
      this.updateHttpClientWrapperMethodlessRequestUrl(request, this.resolveBaseUrl(), this.servicePath),
      handlers
    )
  }

  /**
   * Runs any configured pre-request hooks on the given requeest
   */
  private processPreRequestHooks<R,REQ>(request : R) : R {
    // Process pre-request hooks
    this.preRequestHooks.forEach(preRequestHook => {
      request = <R>(preRequestHook(<HttpClientWrapperMethodlessRequest<REQ>>request))
    })

    // Return a reference to the request
    return request
  }

  /**
   * Returns a new request that is a modified shallow copy of the given request, in that
   * the request URL is modified using the formula:
   * - [the given base URL] + [the given service path] + [the request URL]
   * 
   * @param request       the given request
   * @param baseUrl       the given base URL
   * @param servicePath   the given service path
   */
  private updateHttpClientWrapperRequestUrl<REQ>(
    request     : HttpClientWrapperRequest<REQ>,
    baseUrl     : string,
    servicePath : string

  ) : HttpClientWrapperRequest<REQ> {
    return {
      httpMethod : request.httpMethod,
      url        : baseUrl + servicePath + request.url,
      data       : request.data
    }
  }

  /**
   * Returns a new request that is a modified shallow copy of the given request, in that
   * the request URL is modified using the formula:
   * - [the given base URL] + [the given service path] + [the request URL]
   * 
   * @param request       the given request
   * @param baseUrl       the given base URL
   * @param servicePath   the given service path
   */
  private updateHttpClientWrapperMethodlessRequestUrl<REQ>(
    request     : HttpClientWrapperMethodlessRequest<REQ>,
    baseUrl     : string,
    servicePath : string

  ) : HttpClientWrapperMethodlessRequest<REQ> {
    return {
      url        : baseUrl + servicePath + request.url,
      data       : request.data
    }
  }

  /**
   * Returns one of the base URLs resolved by the baseURLsResolver.
   */
  private resolveBaseUrl() : string {
    // If the base URLs have not been resolved, then do that now
    this.eventuallyResolveBaseURLs()

    // Return one of the resolved base URLs
    return this.getBaseUrl()
  }

  /**
   * Returns one of the base URLs resolved by the base URL resolver. The URLs come
   * in sequence (e.g. first call returns the first base URL, second call returns 
   * the second base URL, and so on until the sequence resets to the first base URL).
   */
  private getBaseUrl() : string {
    return this.baseURLs[this.urlIndexingSequence.next().value]
  }

  /**
   * If the base URLs have not been resolved yet (state determined by the emptiness
   * of the baseURLs array), then the baseURLsResolver is called upon to resolve the
   * base URLs, which are then pushed into the baseURLs array. The urlIndexingSequence
   * is also updated at this point.
   */
  private eventuallyResolveBaseURLs() : void {
    // If there are no baseURLs defined, then resolve them 
    if (this.baseURLs.length == 0) {
      // Call the resolver
      var resolverResponse = this.baseURLsResolver.resolveBaseURLs()

      // If the resolver returned something, then process it
      if (resolverResponse) {
        // If the resolver returned an array of URLs, then push each of the returned URLs into the
        // local baseURLs array
        if ((<string[]>resolverResponse).push) {
          (<string[]>resolverResponse).forEach(baseUrl => {
            this.baseURLs.push(baseUrl)
          })
        }

        // If the resolver returned just one URL, then push that URL into the local baseURLs array
        if ((<string>resolverResponse).charAt) {
          this.baseURLs.push(<string>resolverResponse)
        }
      }

      // If the local baseURLs array is still empty, then throw an exception
      if (this.baseURLs.length == 0) {
        throw "The baseURLsResolver of the [" + this.serviceName + "] service did not resolve any base URL"
      }

      // Update the urlIndexingSequence
      this.urlIndexingSequence = new NumericSequence(0, this.baseURLs.length - 1)
    }
  }

}

/**
 * Specifies an HTTP service configuration, which has a name and an URL resolver.
 * The name uniquely identifies the HTTP service. The URL resolver is resonsible
 * for providing one or more base URLs for one or more instances of the service.
 */
export interface HttpServiceConfig {
  /**
   * The name that uniquely identifies the service within the application scope
   */
  serviceName : string,

  /**
   * Optional base path of the service (goes directly after the base URL when
   * compiling the request URL)
   */
  servicePath? : string,

  /**
   * The URLs resolver for the service (produces a list of base URLs that can be
   * load balanced to access various instances of the service)
   */
  urlsResolver : HttpConnectorBaseURLsResolver,

  /**
   * Optional list of hooks that apply transformations on the request before
   * it is executed (needed for adding authorization headers, amongst other things)
   */
  preRequestHooks? : HttpConnectorPreRequestHook<any>[]
}

/**
 * Defines the configuration item for the HttpConnectorsService
 * 
 */
export type HttpServicesConfig = HttpServiceConfig[]


/**
 * 
 */
@Injectable({
  providedIn: 'root'
})
export class HttpConnectorsService {

  private readonly serviceConnectorsByServiceName : Map<string, HttpConnector> = new Map()

  constructor(
    private readonly httpClientWrapper : HttpClientWrapperService,
    @Inject('HttpServicesConfig') readonly servicesConfig : HttpServicesConfig
  ) {
    // Create a service connector for each service in the services config
    for (let serviceConfig of servicesConfig) {
      // If a service with the same name has already been created (hence configured),
      // then throw an exception
      if (this.serviceConnectorsByServiceName.get(serviceConfig.serviceName)) {
        throw "An HTTP service with the name [" + serviceConfig.serviceName + "] is defined more than once"
      }

      // If all is well, then create the HTTP connector for the service
      this.serviceConnectorsByServiceName.set(
        serviceConfig.serviceName,
        new HttpConnector(
          serviceConfig.serviceName,
          serviceConfig.servicePath ?? "",
          this.httpClientWrapper,
          serviceConfig.urlsResolver,
          serviceConfig.preRequestHooks ?? []
        )
      )
    }
  }

  /**
   * Returns a reference to the HTTP connector for the service with the given service name.
   * If such a connector does not exist, then an exception is thrown.
   * @param serviceName the given service name
   */
  public getHttpConnectorByServiceName(serviceName : string) : HttpConnector {
    // Get a reference to the HTTP connector for the service with the given name
    const httpConnector : HttpConnector | undefined = this.serviceConnectorsByServiceName.get(serviceName)

    // If a reference is found, then return the reference
    if (httpConnector) {
      return httpConnector
    }

    // If no reference is found, then throw an exception
    throw "No HTTP connector defined for the HTTP service named [" + serviceName + "]"
  }
}

/**
 * Convenience method that facilitates the extraction of relevant data from the response
 * body of a given respose object and sending it to the referenced subscriber. The extraction
 * is handled by the given data extractor. Missing data errors are handled. The given error
 * message is sent to the error callback of the subscriber in case of missing data.
 * 
 * @param subscriber                the referenced subscriber
 * @param response                  the given response object
 * @param responseBodyDataExtractor the given data extractor
 * @param missingDataErrorMessage   the given error message
 */
export function bodyProcessingResponseHandler<RES, DAT>(
    subscriber                : Subscriber<DAT>,
    response                  : HttpClientWrapperResponse<any>,
    responseBodyDataExtractor : (response:RES) => DAT,
    missingDataErrorMessage   : string
) : void {
    // Get the response body
    const responseBody : RES = (<any>response.body)

    // If the response has a body, then extract the data using the provided extractor
    const responseBodyData : DAT|undefined = responseBody ? responseBodyDataExtractor(responseBody) : undefined

    // If data has been extracted, then send it up the pipe
    // and send the complete signal to the subscriber
    if (responseBodyData) {
        subscriber.next(responseBodyData)
        subscriber.complete()
    }
    // If no data has been extracted, then send an error up the pipe
    else {
        subscriber.error(missingDataErrorMessage)
    }
}

/**
 * Creates the HttpClientWrapperHandlers to be used with any operation of the HttpConnector or the
 * HttpClientWrapperService for the extraction of relevant data from the response body of a given
 * respose object and sending it to the referenced subscriber. The extraction is handled by the given
 * data extractor. Missing data errors are handled. The given error message is sent to the error
 * callback of the subscriber in case of missing data. System errors are also sent to the subscriber
 * via the error callback.
 * 
 * @param subscriber                the referenced subscriber
 * @param responseBodyDataExtractor the given data extractor
 * @param missingDataErrorMessage   the given error message
 */
export function createBodyProcessingHttpClientWrapperHandlers<RES,DAT,ERR>(
  subscriber                : Subscriber<DAT>,
  responseBodyDataExtractor : (response:RES) => DAT,
  missingDataErrorMessage   : string
) : HttpClientWrapperHandlers<RES,ERR> {
  return {
      responseHandler: response => {
          bodyProcessingResponseHandler(
              subscriber,
              response,
              (responseBody:RES) => responseBodyDataExtractor(responseBody),
              missingDataErrorMessage
          )
      },
      errorHandler: error => {
          subscriber.error(error.body)
      }
  }
}