import { Offre } from "./offre";
import { User } from "./user";

export class Demande {

    id: number;
    statut: string;
    dateDemande: String;
    nbMedailles: number;
    offre :Offre;
    titreOffre: string; 
    user : User;


    constructor(status : string, offre: Offre, date : string)
    {
        this.id=null;
        this.statut=status;
        this.dateDemande =date;
        this.offre =offre;
        this.nbMedailles=offre.nbMedailles;
        this.titreOffre = offre.titre;
    } 
}
