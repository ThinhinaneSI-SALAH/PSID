import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Demande } from "../classes/demande";
import { Offre } from "../classes/offre";

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
    getMyRequestService(email: string): Observable<any> {
        return this.http.get(this.url+"/auth/mesdemandes/"+email);
    }
    getRequestService(sta:String,nbmedailles:number, date:String):Observable<any> {
        return this.http.get(this.url+"/getRequestfilter/"+sta+"/"+nbmedailles+"/"+date);
    }
    saveRequestService(demande:Demande, idOffre:number): Observable<any>
    {
     return this.http.post(this.url+"/addRequestService/"+ idOffre, demande,{ responseType: 'text'});
    }
    deleteServiceRequest(id:number):Observable<any> {
        return this.http.delete(this.url+"/deleteServiceRequest/"+id,{ responseType: 'text'});
    }
    getDemandeId(id: number) :Observable<any> {
        return this.http.get(this.url+"/getRequestServiceById/"+id);
    }
  
}
