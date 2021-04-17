import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
   isLoggedIn =false;
   email : string;
   isLoggedInADM =false;
  constructor() { }

  ngOnInit(): void {
     this.email = sessionStorage.getItem("currentUser")
if(this.email!=null){
      if(this.email==='admin@GMAH.com'){
           this.isLoggedInADM =true

      }
 
       else {  
   
          this.isLoggedIn =true

       }

      }

  else {
    this.isLoggedIn =false

  }

     console.log(this.email);
     console.log("User simple" + this.isLoggedIn)
     console.log("ADMIN" + this.isLoggedInADM)


     // } */
      
  }
  logout() {
    window.sessionStorage.removeItem('currentUser');

    window.sessionStorage.clear();
    window.location.reload();
  }

}
