import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccueilComponent } from './accueil/accueil.component';
import { MesDemandesComponent } from './mes-demandes/mes-demandes.component';
import { MesOffresComponent } from './mes-offres/mes-offres.component';
import { MonProfilComponent } from './mon-profil/mon-profil.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import {HttpClientModule} from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { UpdateProfilComponent } from './update-profil/update-profil.component';
import { CreateOffreComponent } from './create-offre/create-offre.component';
import { OffreListComponent } from './offre-list/offre-list.component';
import { OffreDetailComponent } from './offre-detail/offre-detail.component';
import { UpdateOffreComponent } from './update-offre/update-offre.component';

@NgModule({
  declarations: [
    AppComponent,
    AccueilComponent,
    MesDemandesComponent,
    MesOffresComponent,
    MonProfilComponent,
    LoginComponent,
    NavComponent,
    RegisterComponent,
    UserComponent,
    UpdateProfilComponent
    InscriptionComponent,
    NavComponent,
    CreateOffreComponent,
    OffreListComponent,
    OffreDetailComponent,
    UpdateOffreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule

  ],
  providers: [
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

