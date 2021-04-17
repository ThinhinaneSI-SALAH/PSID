import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  isLoggedIn : Boolean;
   email : string;
  constructor() { }

  ngOnInit(): void {
     this.email = sessionStorage.getItem("currentUser")
     sessionStorage.getItem("idUser");
     console.log(this.email);
     if (this.email !=null ){
      this.isLoggedIn =true

     }
     else {
      this.isLoggedIn = false

     }



  }
  logout() {
    window.sessionStorage.removeItem('currentUser');
    window.sessionStorage.removeItem('idUser');
    window.sessionStorage.clear();
    window.location.reload();
  }

}
