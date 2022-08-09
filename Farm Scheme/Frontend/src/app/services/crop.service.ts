import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CropService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  getAllCrop() {
    return this.httpClient.get(this.url + "/crop/getAllCrop");
  }
}
