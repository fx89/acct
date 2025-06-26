import { HttpClient, HttpErrorResponse, HttpEvent, HttpEventType, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

/**
 * Ennumerates the HTTP methods supported by the HttpClientWrapperService
 */
export enum HttpClientWrapperHttpMethod {
  GET    = "GET",
  POST   = "POST",
  PUT    = "PUT",
  DELETE = "DELETE"
}

/**
 * Specifies the upload or download progress of a HTTP request operation
 */
export interface HttpClientWrapperRequestProgress {
  loaded : number
  total  : number | undefined
}

/**
 * Defines the content that can be sent with an HTTP request that does not support a request body
 */
export interface HttpClientWrapperBodylessRequestData {
  headers? : Record<string, string | string[]>
  params?  : Record<string, string | number | boolean | ReadonlyArray<string | number | boolean>>
}

/**
 * Defines the content that can be sent with an HTTP request as being the content of a request that
 * does not support a request body plus the request body
 */
export interface HttpClientWrapperRequestData<REQ> extends HttpClientWrapperBodylessRequestData {
  body? : REQ
}

/**
 * Specifies the contents of an HTTP request, without specifying the HTTP method
 */
export interface HttpClientWrapperMethodlessRequest<REQ> {
  url   : string,
  data? : HttpClientWrapperRequestData<REQ>
}

/**
 * Specifies the contents of an HTTP request as being those of a methodless request plus the HTTP method
 */
export interface HttpClientWrapperRequest<REQ> extends HttpClientWrapperMethodlessRequest<REQ> {
  httpMethod : HttpClientWrapperHttpMethod,
}

/**
 * Specifies the contents of an HTTP GET request
 */
export class HttpClientWrapperGetRequest implements HttpClientWrapperRequest<undefined> {
  public httpMethod : HttpClientWrapperHttpMethod = HttpClientWrapperHttpMethod.GET
  public data       : HttpClientWrapperRequestData<undefined>

  constructor(
    public url : string,
    data?      : HttpClientWrapperBodylessRequestData
  ){
    this.data = {
      headers : data?.headers,
      params  : data?.params
    }
  }
}

/**
 * Specifies the contents of an HTTP POST request
 */
export class HttpClientWrapperPostRequest<REQ> implements HttpClientWrapperRequest<REQ> {
  public httpMethod : HttpClientWrapperHttpMethod = HttpClientWrapperHttpMethod.POST

  constructor(
    public url   :string,
    public data? :HttpClientWrapperRequestData<REQ>
  ){}
}

/**
 * Specifies the contents of an HTTP PUT request
 */
export class HttpClientWrapperPutRequest<REQ> implements HttpClientWrapperRequest<REQ> {
  public httpMethod : HttpClientWrapperHttpMethod = HttpClientWrapperHttpMethod.PUT

  constructor(
    public url   :string,
    public data? :HttpClientWrapperRequestData<REQ>
  ){}
}

/**
 * Specifies the contents of an HTTP DELETE request
 */
export class HttpClientWrapperDeleteRequest implements HttpClientWrapperRequest<undefined> {
  public httpMethod : HttpClientWrapperHttpMethod = HttpClientWrapperHttpMethod.DELETE
  public data       : HttpClientWrapperRequestData<undefined>

  constructor(
    public url : string,
    data?      : HttpClientWrapperBodylessRequestData
  ){
    this.data = {
      headers : data?.headers,
      params  : data?.params
    }
  }
}

/**
 * Specifies the structure of an HTTP response within the HttpClient wrapper
 */
export interface HttpClientWrapperResponse<RES> {
  body       : RES,
  headers    : HttpHeaders,
  statusCode : number
}

/**
 * Defines the signature of the function that handles a successful HTTP response
 */
export type HttpClientWrapperResponseHandler<RES> = (succeessResponse: HttpClientWrapperResponse<RES>) => void

/**
 * Defines the signature of the function that handles an HTTP error response
 */
export type HttpClientWrapperErrorHandler<ERR> = (errorResponse: HttpClientWrapperResponse<ERR>) => void

/**
 * Defines the signature of the function that handles upload or download progress updates
 */
export type HttpClientWrapperProgressHandler = (progress: HttpClientWrapperRequestProgress) => void

/**
 * Specifies the handlers for the success, error and progress events of the HttpClient
 */
export interface HttpClientWrapperHandlers<RES,ERR> {
  responseHandler? : HttpClientWrapperResponseHandler<RES>
  errorHandler?    : HttpClientWrapperErrorHandler<ERR>
  progressHandler? : HttpClientWrapperProgressHandler
}

/**
 * Provides a high-level interface for basic HttpClient functionality.
 * See https://angular.dev/guide/http/making-requests.
 */
@Injectable({
  providedIn: 'root'
})
export class HttpClientWrapperService {

  constructor(private httpClient:HttpClient) { }

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
    const observableResponse : Observable<HttpEvent<any>> =
      this.httpClient.request(
        request.httpMethod,
        request.url,
        {
          headers        : request.data?.headers,
          params         : request.data?.params,
          body           : request.data?.body,
          reportProgress : true,
          observe        : 'events'
        }
      )

    observableResponse.subscribe({
      // Standard response
      next(event) {
        switch (event.type) {
          // Handle the upload progress event
          case HttpEventType.UploadProgress:
            if (handlers?.progressHandler) {
              handlers?.progressHandler({
                loaded: event.loaded,
                total : event.total
              })
            }
            break;

          // Handle the download progress event
          case HttpEventType.DownloadProgress:
            if (handlers?.progressHandler) {
              handlers?.progressHandler({
                loaded: event.loaded,
                total : event.total
              })
            }
            break;

          // Handle the response event by splitting it into success and error cases
          case HttpEventType.Response:
            // If the status code represents an OK status, then this is the success case
            if (event.ok) {
              // If the response handler is defined, then call it
              if (handlers?.responseHandler) {
                handlers?.responseHandler(
                  {
                    body: event.body,
                    headers: event.headers,
                    statusCode: event.status
                  }
                )
              }
            }
            // If this is not the success case, then it's the error case
            else {
              // If the error handler is defined, then call it
              if (handlers?.errorHandler) {
                handlers?.errorHandler(
                {
                  body: event.body,
                  headers: event.headers,
                  statusCode: event.status
                }
              )
              }
            }
            break;
        }
      },

      // Errors in the HttpClient (i.e. parsing errors)
      error(err:HttpErrorResponse) {
        
        // If the error handler is defined, then call it
        if (handlers?.errorHandler) {
          handlers?.errorHandler(
          {
            body: (<any> err.error),
            headers: err.headers,
            statusCode: err.status
          }
        )
        }
      }
    })
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
  ) : void {
    this.request(
      new HttpClientWrapperGetRequest(
        request.url,
        request.data
      ),
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
  ) : void {
    this.request(
      new HttpClientWrapperPostRequest(
        request.url,
        request.data
      ),
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
  ) : void {
    this.request(
      new HttpClientWrapperPutRequest(
        request.url,
        request.data
      ),
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
  ) : void {
    this.request(
      new HttpClientWrapperDeleteRequest(
        request.url,
        request.data
      ),
      handlers
    )
  }

}

/**
 * Sets the given value for the given key in the headers of the referenced request.
 * If the key already exists, then it is overwritten.
 * @param request the referenced request
 * @param key     the given key
 * @param value   the given value
 */
export function setRequestHeader<REQ>(
  request : HttpClientWrapperMethodlessRequest<REQ>,
  key     : string,
  value   : string
) : void
{
  if (!request.data) {
    request.data = {}
  }

  if (!request.data.headers) {
    request.data.headers = {}
  }

  request.data.headers[key] = value
}