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
  constructor(private offreService: OffreServiceService,private router: Router,private http: HttpClient,private route:ActivatedRoute) { }

  ngOnInit(): void {
   /* this.offres = ActivatedRoute..pipe(map(d => d.offres)
    if(this.route.snapshot.paramMap.get('offres')!=null)
    {
      
      this.offres=(this.route.snapshot.paramMap.get);
    }
    else
    {
      this.reloadData();
    }*/
   
    this.reloadData();
    
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

  FilterOffre ():void
  {
    var date:String;
    var nbmedailles:number;
    var categorie:String;
    var ville:String;
    var motcle :String;
    
    date = document.getElementsByName("datefin")[0]["value"];
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
    
    this.router.navigate(['accueil', this.offres]);
  }

}
