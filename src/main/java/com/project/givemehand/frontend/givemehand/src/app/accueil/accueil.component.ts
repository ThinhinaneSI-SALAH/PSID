import { Component, ElementRef, OnInit, ViewChild,NgZone } from '@angular/core';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import {Offre} from '../classes/offre';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Filtre } from '../classes/filtre';


@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  empty =false;
  offres: Observable<Offre[]>;
  categories : Observable< String[]>;
  lat: number = 48.856614;
  lng: number = 2.3522219;
  zoom:number = 50;
  address: string = "";
  private geoCoder;
  filtre: Filtre;

  @ViewChild('search')
  public searchElementRef: ElementRef;


  constructor(private offreService: OffreServiceService,private router: Router,private http: HttpClient,private route:ActivatedRoute, private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) { }

  ngOnInit(): void {
    this.filtre = new Filtre();
    this.categories = this.offreService.getCategories();
    /*if(this.route.snapshot.paramMap.get('offre')!=null)
    {
        console.log("on est ds la page filtre");
        this.FilterOffre();
    }
    else
    {    */
      this.reloadData();
   // }

    //*Google Maps

    this.mapsAPILoader.load().then(() => {
      this.setCurrentLocation();
      this.geoCoder = new google.maps.Geocoder;

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
          console.log(place);
          this.address = place.formatted_address;
          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
          this.zoom = 50;
        });
      });
    });
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


  /*
  ** Récupérer la position actuelle
  */
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.zoom = 15;
      });
    }
  }

  markerDragEnd($event: MouseEvent) {
    console.log($event);
    this.lat = $event.coords.lat;
    this.lng = $event.coords.lng;
    this.getAddress(this.lat, this.lng);
  }


  getAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      console.log(results);
      console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }

    });
  }

  filtrer(){
    if(this.address !=""){
      this.filtre.ville = this.address;
    }
    this.offres = this.offreService.filtrer(this.filtre);
    this.address ="";
  }


}




