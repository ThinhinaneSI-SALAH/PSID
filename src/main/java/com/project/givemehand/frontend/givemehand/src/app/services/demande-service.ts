import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  
  @Injectable({
    providedIn: 'root'
  })

export class DemandeService {
    public url =environment.api_url;

    constructor(private http: HttpClient) { 

    }

    getMyRequestService(id_user: number): Observable<any> {
        return this.http.get(this.url+"/auth/mesdemandes/"+id_user);
    }
    getRequestService(sta:String,nbmedailles:number, date:String):Observable<any> {
        return this.http.get(this.url+"/getRequestfilter/"+sta+"/"+nbmedailles+"/"+date);
    }
  
}
