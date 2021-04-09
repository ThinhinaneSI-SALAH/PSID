import { Component, OnInit } from '@angular/core';
import {Offre} from '../classes/offre';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  empty =false;
  offres: Observable<Offre[]>;
  categories : Observable< String[]>;
  constructor(private offreService: OffreServiceService,private router: Router,private http: HttpClient,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.categories = this.offreService.getCategories();
    if(this.route.snapshot.paramMap.get('offre')!=null)
    {
        console.log("on est ds la page filtre");
        this.FilterOffre();
    }
    else
    {    
      console.log("on est ds la page accueil");
      this.reloadData();
    }    
  }
  
  reloadData() {
    this.offres = this.offreService.getOffreList();

    this.offres.subscribe((value) => {
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

  FilterOffre (): void
  {
    var date:String;
    var nbmedailles:number;
    var categorie:String;
    var ville:String;
    var motcle :String;
    
    date = document.getElementsByName("datefin")[0]["value"];
    let dates  = date.split('/');
    date= dates[0]+ dates[1]+dates[2];
    nbmedailles=document.getElementsByName("nbmedailles")[0]["value"];
    ville=document.getElementsByName("ville")[0]["value"];
    motcle=document.getElementsByName("motcle")[0]["value"];
    categorie=document.getElementsByName("categorie")[0]["value"];
    
    this.offres = this.offreService.getofferfilter(categorie,ville,nbmedailles,motcle,date);
     
    this.offres.subscribe((value) => {
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

}
