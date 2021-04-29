import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mon-profil',
  templateUrl: './mon-profil.component.html',
  styleUrls: ['./mon-profil.component.scss']
})
export class MonProfilComponent implements OnInit
{
  isLoggedIn : Boolean;
  isLoginFailed : Boolean;
  constructor() { }

  ngOnInit(): void {
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    console.log("You are logged in");
    console.log(this.isLoggedIn);
  }
  //window.location.reload();

}
