import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-create-offre',
  templateUrl: './create-offre.component.html',
  styleUrls: ['./create-offre.component.scss']
})
export class CreateOffreComponent implements OnInit {
  
 offres:Createoffer = new Createoffer();
  empty =false;
  submitted=false;
  categories : Observable< String[]>;
   constructor(private offreService: OffreServiceService,private router: Router,private http: HttpClient) 
   { } 
  
  ngOnInit(): void {
    this.categories = this.offreService.getCategories();
  }
  
  newOffre():void
  {
   
    this.submitted=false;
    this.offres= new Createoffer();
  }

  save()
  { 
    console.log("creation d'offres");
    this.offreService.saveoffer(this.offres).subscribe(data => {
      console.log(this.offres)
      console.log(data)
     this.offres = new Createoffer();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['accueil']);
  }

}
