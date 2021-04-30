import { Offre } from "./offre";
import { User } from "./user";
import { DatePipe, formatDate } from "@angular/common";

export class Demande {

    id: number;
    statut: string;
    dateDemande: string;
    nbMedailles: number;
    offre :Offre;
    titreOffre: string; 
    user : User;
    buttonDisabled : boolean;

    constructor(status : string,  date:string, offre: Offre, user:User)
    {
        this.statut=status;
        this.dateDemande = date;
        this.offre =offre;
        this.user = user;

    }

}
