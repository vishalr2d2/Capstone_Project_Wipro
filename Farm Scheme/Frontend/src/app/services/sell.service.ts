import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  sellRequest(data: any) {
    return this.httpClient.post(this.url +
      "/sell/sellRequest", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getSellRequestByFarmer(id:any) {
    return this.httpClient.get(this.url + "/sell/getSellRequestByFarmer/"+id);
  }

  getAllSellRequest() {
    return this.httpClient.get(this.url + "/sell/getAllSellRequest");
  }
}
