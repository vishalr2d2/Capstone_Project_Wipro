import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BidderService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  register(data: any) {
    return this.httpClient.post(this.url +
      "/bidder/register", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }
}
