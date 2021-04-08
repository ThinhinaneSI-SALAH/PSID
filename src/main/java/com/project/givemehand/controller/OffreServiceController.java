package com.project.givemehand.controller;
import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OffreServiceController
{
    @Autowired
    private OffreService service;

    @GetMapping("/getofferfilter/categorie={categorie}/ville={ville}/date={date}/nbmedaille={nbmedaille}" +
            "motcle={motcle}/")

    public List<Offre> getoffrefilter(@PathVariable(value = "categorie") Categorie categorie, @PathVariable(value = "ville") String  ville,
                                      @PathVariable(value = "date") Date date , @PathVariable(value = "nbmedaille") int  nbmddaille,@PathVariable(value = "motcle") String motcle)
    {
        Filtre f = new Filtre(categorie,ville,date,nbmddaille,motcle);
        return service.filtrerOffre(f);
    }

}
