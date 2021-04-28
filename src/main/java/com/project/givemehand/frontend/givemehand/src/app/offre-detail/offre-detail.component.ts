import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {Demande} from '../classes/demande';

import {OffreServiceService} from '../services/offre-service.service';
import {DemandeService} from '../services/demande-service';

import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-offre-detail',
  templateUrl: './offre-detail.component.html',
  styleUrls: ['./offre-detail.component.scss']
})
export class OffreDetailComponent implements OnInit {
 id:number;
 offres:Createoffer;
 demandes : Observable<Demande[]>;
status : Observable< String[]>;

  constructor(private offreService: OffreServiceService,private demandeService : DemandeService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.offres= new Createoffer();
    this.id=this.route.snapshot.params['id'];
    this.offreService.getofferid(this.id).subscribe(data => {
      console.log(data)
      this.offres = data;
    }, error => console.log(error));

    this.status= this.demandeService.getAllStatus();

    this.demandeService.getAllDemandesByOffer(this.id).subscribe(data => {
      console.log(data)
      this.demandes= data;
    }, error => console.log(error));
}
list(){
  this.router.navigate(['/offrelist']);
}

updateDemande(demande: Demande, idDemande : number){
  console.log(demande);
  console.log(idDemande);

  this.demandeService.updateDemande(demande,idDemande)    
  .subscribe(data => {
    console.log(data);
    this.offres = new Createoffer();
    this.list();
  }, error => console.log(error));


}

}




