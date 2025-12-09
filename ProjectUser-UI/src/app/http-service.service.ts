import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  // GET Request
  get<T>(url: string, params?: any, headers?: any): Observable<T> {
    const httpParams = new HttpParams({ fromObject: params });
    const httpHeaders = new HttpHeaders(headers);
    return this.http.get<T>(url, { params: httpParams, headers: httpHeaders });
  }

  // POST Request
  post<T>(url: string, body: any, headers?: any): Observable<T> {
    const httpHeaders = new HttpHeaders(headers);
    return this.http.post<T>(url, body, { headers: httpHeaders });
  }

  // PUT Request (Optional)
  put<T>(url: string, body: any, headers?: any): Observable<T> {
    const httpHeaders = new HttpHeaders(headers);
    return this.http.put<T>(url, body, { headers: httpHeaders });
  }

  // DELETE Request (Optional)
  delete<T>(url: string, headers?: any): Observable<T> {
    const httpHeaders = new HttpHeaders(headers);
    return this.http.delete<T>(url, { headers: httpHeaders });
  }
}