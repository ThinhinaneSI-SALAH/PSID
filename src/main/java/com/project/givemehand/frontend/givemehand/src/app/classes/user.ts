import { DatePipe } from "@angular/common";
import { Adresse } from "./adresse";

export class User {

        id:number;
        email : string;
        password : string;
        firstName : string;
        lastName : string;
        phoneNumber : string;
        street: string;
        city : string;
        zip : string;
        country: string;
        medailles: number;
    
        /*constructor(username: string , email : String, motDePassse : String,
            github: String)
        {
            this.username=username;
            this.email=email;
            this.motDePasse=motDePassse;
            this.github=github;
        }  */
}
