import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Demande } from '../classes/demande';
import { DemandeService } from '../services/demande-service';
import { OffreServiceService } from '../services/offre-service.service';

@Component({
  selector: 'app-update-demande',
  templateUrl: './update-demande.component.html',
  styleUrls: ['./update-demande.component.scss']
})
export class UpdateDemandeComponent implements OnInit {
  id:number;
  titre: string;
  description :string;
  demande:Demande;
  submitted=false;

  constructor(private demandeService: DemandeService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.submitted=false;
    this.demande= new Demande(null,null,null,null);
  
    this.id=this.route.snapshot.params['id']
    console.log(this.id);
    this.demandeService.getDemandeId(this.id).subscribe(data => {
        console.log(data)
        this.demande = data;
    }, error => console.log(error));
  }

}
