import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../classes/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private  baseUrl = environment.api_url;

  constructor(private http: HttpClient) { }

  public  updateUser( user: User) : Observable<User>{
    return this.http.put<User>(`${this.baseUrl}/auth/updateUser`,user);
  }

  public getUserByEmail(email: string){
    return this.http.get(`${this.baseUrl}/auth/findUserByemail/${email}`);
  }
  public getUserById(id: number){
    return this.http.get(`${this.baseUrl}/auth/finduserById/${id}`);
  }

  getUserList(): Observable<any> {
    return this.http.get(this.baseUrl+'/auth/getAllUser');
  }
    deleteUser(id:number):Observable<any> {
    return this.http.delete(this.baseUrl+"/auth/deleteUser/"+id,{ responseType: 'text'});
  public findIdUserByMail(email: string){
    return this.http.get(`${this.baseUrl}/auth/findIdUserByMail/${email}`);
  }
}
