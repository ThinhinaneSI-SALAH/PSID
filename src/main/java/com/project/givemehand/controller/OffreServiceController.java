package com.project.givemehand.controller;
import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OffreServiceController
{
    @Autowired
    private OffreService service;

    @RequestMapping(path ="/getofferfilter/{categorie}/{ville}/{nbMedailles}/{motcle}/{date}", method = RequestMethod.GET)

    public List<Offre> getoffres(@PathVariable String categorie, @PathVariable String ville, @PathVariable String nbMedailles, @PathVariable String motcle, @PathVariable String date)
    {
        System.out.println("Catégorie"+categorie);
        Categorie cat = Categorie.valueOf(categorie);
        String jour = date.substring(0,2);
        String mois = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        System.out.println(d);
        Filtre f = new Filtre(cat,ville, new Date(d),Integer.parseInt(nbMedailles),motcle);
        return service.filtrerOffre(f);
    }


}
