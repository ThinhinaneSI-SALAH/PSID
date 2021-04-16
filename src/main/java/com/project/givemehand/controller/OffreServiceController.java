package com.project.givemehand.controller;
import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.services.OffreService;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
/**
 *  Elle represente le comtroller de la classe OffreService
 */
public class OffreServiceController
{
    @Autowired
    private OffreService service;
    private UserService userService;
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

    @RequestMapping(path ="/getAllOffer", method = RequestMethod.GET)
    public List<Offre> getAlloffres()
    {

        return service.getAlloffres();
    }

    @RequestMapping(path ="/getAllOfferUser/{id", method = RequestMethod.GET)
    public List<Offre> getOfferByUser(@PathVariable long id)
    {
        return  service.getOfferByUser(id);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Offre findById(@PathVariable Long id) {

        return service.getOfferById(id);
    }

    /*
    API renvoyant la liste des catégorie des offres
     */
    @RequestMapping(path ="/getAllCat", method = RequestMethod.GET)
    public Categorie[] getAllCat(){
        Categorie[] categories = Categorie.values();
        return categories;
     }


    //creer une offre
    @RequestMapping(value = "/CreateOffer", method = RequestMethod.POST)  //ok
    public void SaveOffre(@RequestBody Offre offres)
    {
        service.save(offres);
    }

    //creer une offre et userID
    @RequestMapping(value = "/CreateAllOffer", method = RequestMethod.POST)  //ok
    public void SaveALLOffre(@RequestBody Offre offres, User user)
    {
        service.saveALL(offres,user);
    }


   //Update_offer
    @PutMapping("/UpdateOffer")  //encours
    public ResponseEntity<Offre> update(@RequestBody Offre offre)
    {
        return ResponseEntity.ok(service.update(offre));
    }

    //Supprimer une offer par id //ok
    @DeleteMapping("/DeleteOffer/{offreid}")
    public void deleteBook(@PathVariable("offreid") long id)
    {
        service.deleteoffer(id);
    }
    /*
    @RequestMapping(value = "/OfferById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offre> getOfffertByUser(@PathVariable Long userid)
    {
        //User user = userService.findById(userid);
        return service.getOfferByUser(userid);
    }*/
}
