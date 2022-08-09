import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InsuranceService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  calculate(data: any) {
    return this.httpClient.post(this.url +
      "/insurance/calculate", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  applyForInsurance(data: any) {
    return this.httpClient.post(this.url +
      "/insurance/applyForInsurance", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getInsuranceById(pId:any) {
    return this.httpClient.get(this.url + "/insurance/getInsuranceById/"+pId);
  }

  claimInsurance(data: any) {
    return this.httpClient.post(this.url +
      "/insurance/claimInsurance", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }
}
