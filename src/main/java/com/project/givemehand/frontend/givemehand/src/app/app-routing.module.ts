import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { MesDemandesComponent } from './mes-demandes/mes-demandes.component';
import { MesOffresComponent } from './mes-offres/mes-offres.component';
import { MonProfilComponent } from './mon-profil/mon-profil.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { UpdateProfilComponent } from './update-profil/update-profil.component';

//import { OffreFiltres} from './accueil/accueil.component';;

const routes: Routes = [
 // { path: '', redirectTo: '/accueil', pathMatch: 'full' },
  { path: 'accueil', component: AccueilComponent },
  { path: 'mesOffres', component: MesOffresComponent },
  { path: 'mesDemandes', component: MesDemandesComponent},
  { path: 'monProfil', component: MonProfilComponent },
  { path: 'inscription', component: RegisterComponent },
  { path: 'connexion', component: LoginComponent },
  { path: 'updateprofil', component:  UpdateProfilComponent },

 // { path: 'accueil/:id', component: OffreFiltres }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
