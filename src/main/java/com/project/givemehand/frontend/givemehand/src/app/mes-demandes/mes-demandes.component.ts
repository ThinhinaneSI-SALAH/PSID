import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { NumberLiteralType } from 'typescript';
import {Demande} from '../classes/demande';
import { User } from '../classes/user';
import {DemandeService} from '../services/demande-service'
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-mes-demandes',
  templateUrl: './mes-demandes.component.html',
  styleUrls: ['./mes-demandes.component.scss']
})
export class MesDemandesComponent implements OnInit {

  empty =false;
  demandes: Observable<Demande[]>;
  statut : Observable< String[]>;
  medaille:string;
  user: any;

  constructor(private demandeService: DemandeService,private userService: UserService,private router: Router,private http: HttpClient,private route:ActivatedRoute) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('demande')!=null)
    {
      this.filterDemande();
    }
    else{ 
      this.reloadData();
    }  
  }

  reloadData() {
    this.demandes = this.demandeService.getMyRequestService(1);

    this.demandes.subscribe((value) => {
      console.log(value);
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
    }, () => {
      console.log('Fini !');
    });
  }

  filterDemande (): void
  {
    var date:String;
    var nbmedailles:number;
    var statut:String;
    
    date = document.getElementsByName("dateDemande")[0]["value"];
    let dates  = date.split('-');
    date= dates[2]+ dates[1]+dates[0];
    nbmedailles=document.getElementsByName("nbMedailles")[0]["value"];
    statut=document.getElementsByName("statut")[0]["value"];
    console.log('stat',statut)
    console.log(nbmedailles);
    console.log(date);
    this.demandes = this.demandeService.getRequestService(statut,nbmedailles,date);
     
    this.demandes.subscribe((value) => {
      console.log(value);
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
    }, () => {
      console.log('Fini !');
    });  
  }
  /** 
  afficheRange() {
    let R=document.getElementById("Range");
    this.medaille =document.getElementById("nbMedailles").innerHTML="Valeur="+R;
  }**/
}
