import { Injectable } from '@angular/core';

import { HttpClientWrapperService, RequestType, ResponseType } from '../http-client-wrapper/http-client-wrapper.service';
import { Observable } from 'rxjs';
import { Identifiable } from 'src/app/model/identifiable';
import { environment } from 'src/environments/environment';
import { ConfigurationService } from '../configuration-service/configuration.service';



@Injectable({
  providedIn: 'root'
})
export class DataService {
    constructor( private httpClientWrapper : HttpClientWrapperService, private configuration : ConfigurationService) {
        this.httpClientWrapper.setBaseUrl(configuration.getAttributeValue("backendUrl"));
    }

    public list<R extends Identifiable[]>(basePath:string) : Observable<R> {
        return this.httpClientWrapper.request( RequestType.GET, basePath + "/list", null);
    }

    public save<R extends Identifiable>(basePath:string, item : R, customOperation : string = null) : Observable<R> {
        return this.httpClientWrapper.request( RequestType.POST, basePath + (customOperation ? ("/" + customOperation) : "/save"), item );
    }

    public delete<R extends Identifiable>(basePath:string, item : R) : Observable<String> {
        return this.httpClientWrapper.request(RequestType.DELETE, basePath + "/delete", null, new Map([["id", item.id.toFixed()]]));
    }

    public get<R>(url: string, params?: Map<string, string>) : Observable<R> {
        return this.httpClientWrapper.request(RequestType.GET, url, null, params);
    }

    public post<R>(url: string, params?: Map<string, string>) : Observable<R> {
        return this.httpClientWrapper.request(RequestType.POST, url, null, params);
    }
}

