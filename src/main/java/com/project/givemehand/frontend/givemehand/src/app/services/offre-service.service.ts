import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from "../../environments/environment";
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class OffreServiceService {
  public url =environment.api_url;

  constructor(private http: HttpClient) { }
  
  getOffreList(): Observable<any> {
    return this.http.get(this.url+'/getAllOffer');
  }
}
