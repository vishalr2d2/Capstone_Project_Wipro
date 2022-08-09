import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FarmerService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  register(data: any) {
    return this.httpClient.post(this.url +
      "/farmer/register", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  login(data: any) {
    return this.httpClient.post(this.url +
      "/farmer/login", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getAllFarmer() {
    return this.httpClient.get(this.url + "/farmer/getAllFarmer");
  }
}
