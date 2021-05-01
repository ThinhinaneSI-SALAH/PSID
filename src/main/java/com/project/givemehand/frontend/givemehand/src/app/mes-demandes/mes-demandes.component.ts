import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { from, Observable } from 'rxjs';
import { NumberLiteralType } from 'typescript';
import {Demande} from '../classes/demande';
import { User } from '../classes/user';
import {DemandeService} from '../services/demande-service'
import { UserService } from '../services/user.service';
import {NoteserviceService} from '../services/noteservice.service';

import{OffreServiceService} from '../services/offre-service.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

//import { Console } from 'node:console';


@Component({
  selector: 'app-mes-demandes',
  templateUrl: './mes-demandes.component.html',
  styleUrls: ['./mes-demandes.component.scss']
})
export class MesDemandesComponent implements OnInit {
  currentRate =0;
  empty =false;
  demandes: Demande[]
  statut : Observable< String[]>
  medaille:string;
  moyennenote:Observable<any>;
  id:number;


  constructor(private demandeService: DemandeService,private noteService: NoteserviceService,
    private userService: UserService,  private config: NgbRatingConfig,private offreService:OffreServiceService,private router: Router,private http: HttpClient,private route:ActivatedRoute) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('demande')!=null)
    {
      this.filterDemande();
    }
    else{
      this.reloadData();
    }
    //this.config.readonly = true;

  }

  reloadData() {
    let email = sessionStorage.getItem("currentUser");
     this.demandeService.getMyRequestService(email).subscribe((value) => {
       this.demandes=value
      this.demandes.forEach(element => {
        console.log("le statut de la demande est:",element.statut)
        if(element.statut==="TERMINE" && element.is_noted==false)
        {
          this.config.readonly = false;

        }
        else 
        {
          this.config.readonly=true;
        }
      });
      console.log(value);
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
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
    this.demandeService.getRequestService(statut,nbmedailles,date).subscribe((value) => {
      this.demandes=value
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

  saveNote(currentRate,demande,idDemande){
    //CurrenteRate=Note
    //demande = ID_offre
    console.log("Note",currentRate);
    console.log("Offre ID:",demande);
    //console.log(this.noteService.saveNote(currentRate,demande));
    this.noteService.saveNote(currentRate,demande).subscribe(value =>{
      this.demandeService.UpdateIsNoted(idDemande).subscribe(isnoted=>
      {
          console.log("l'id de la demande est ",isnoted)
      }, error => console.log(error));
      this.offreService.getmoyenne(demande).subscribe(dataVM => {
        console.log(this.moyennenote=dataVM)
        console.log("Data" + dataVM);
        // this.listDemande(this.id);
      this.offreService.updatemoyenne(demande,dataVM).subscribe(data => {
        console.log(data)
        }, error => console.log(error));
        }, error => console.log(error));
        // this.offres = new Createoffer();
        //this.list();
      console.log("la moyenne est :",this.moyennenote)
      console.log(value);
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
    });

  }


  deleteServiceRequest (id:number) {
    this.demandeService.deleteServiceRequest(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
      this.reloadData();
  }

}
