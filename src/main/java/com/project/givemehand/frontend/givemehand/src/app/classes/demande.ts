import { Offre } from "./offre";
export class Demande {

    id: number;
    statut: string;
    dateDemande: String;
    nbMedailles: number;
    offre :Offre;
    titreOffre: string; 


    constructor(status : string,  date:string, offre: Offre)
    {
        this.id=null;
        this.statut=status;
        this.dateDemande =date;
        this.offre =offre;
        this.nbMedailles=offre.nbMedailles;
        this.titreOffre = offre.titre;
    }
}
