import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-profil',
  templateUrl: './update-profil.component.html',
  styleUrls: ['./update-profil.component.scss']
})

export class UpdateProfilComponent implements OnInit {
  user :  any; 
  semail : string;
 submitted: any;
Boolean = false;s
errorMessage = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.user = new User();
    this.semail = sessionStorage.getItem("currentUser")
    console.log("user test ")
    console.log("User connected : " +   this.semail)

    this.userService.getUserByEmail(this.semail)
      .subscribe(data => {
        console.log("user data")
        console.log(data)
        this.user= data;
      },
      err => {
        this.errorMessage = err.error.message;
       
      }
    );
  }
  onSubmit(){
    this.submitted = true;
    this.userService.updateUser(this.user.email,this.user).subscribe(
      data=> console.log(data),
      error => console.log(error)
    );
  }

  }
  

  /* */


