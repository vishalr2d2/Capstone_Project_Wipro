import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  purchaseRequest(data: any) {
    return this.httpClient.post(this.url +
      "/purchase/purchaseRequest", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getAllPurchaseRequestByRequestId(id:any) {
    return this.httpClient.get(this.url + "/purchase/getAllPurchaseRequestByRequestId/"+id);
  }

  approvePurchaseRequest(srId:any,biId:any) {
    return this.httpClient.get(this.url + "/purchase/approvePurchaseRequest/"+srId+"/"+biId);
  }

  getSellRequestByIdAndStatus(frId:any,status:any) {
    return this.httpClient.get(this.url + "/purchase/getSellRequestByIdAndStatus/"+frId+"/"+status);
  }

  getMaxBid(srId:any) {
    return this.httpClient.get(this.url + "/purchase/getMaxBid/"+srId);
  }
  
}
