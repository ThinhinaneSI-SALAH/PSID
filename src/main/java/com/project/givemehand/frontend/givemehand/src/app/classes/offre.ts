import { DatePipe } from "@angular/common";

export class Offre {
    id: number;
    titre: string;
    description: string;
    cat: string;
    villeOffre: string;
    dateFinOffre: String;
    nbMedailles: number;
   
  
    constructor(description: string,id: number,titre: string, categorie: string,villeOffre: string, dateFinOffre: Date, nbMedailles: number,datePipe:DatePipe ) {
      this.id = id;
      this.description= description;
      this.cat= categorie;
      this.titre =titre;
      this.villeOffre =villeOffre;
      //this.dateFinOffre = new Date(dateFinOffre.getTime() - (dateFinOffre.getTimezoneOffset() * 60000 )).toISOString().split("T")[0];
      this.dateFinOffre = datePipe.transform(dateFinOffre, 'yyyy-MM-dd');
      this.nbMedailles = nbMedailles;
    }
}
