import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {OffreServiceService} from '../services/offre-service.service';
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
  constructor(private offreService: OffreServiceService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.offres= new Createoffer();
    this.id=this.route.snapshot.params['id'];
    this.offreService.getofferid(this.id).subscribe(data => {
      console.log(data)
      this.offres = data;
    }, error => console.log(error));
}
list(){
  this.router.navigate(['/accueil']);
}
  }


