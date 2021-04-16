package com.project.givemehand.controller;

import com.project.givemehand.models.entity.*;
import com.project.givemehand.services.RequestService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
/**
 *  Elle represente le controller de la classe demandeService
 */
public class DemandeServiceController {

    @Autowired
    private RequestService service;

    @RequestMapping(path ="/requestServiceById/{id}", method = RequestMethod.GET)
    public Demande getServiceRequest( @PathVariable("id") Long id)
    {
        return service.getServiceRequest(id);
    }

    @RequestMapping(path ="/addRequestService", method = RequestMethod.POST)
    public ResponseEntity<Demande> addDemande(@RequestBody Demande demande)
    {
        return service.addRequestService(demande);
    }

    @DeleteMapping(value = "/deleteServiceRequest/{requestId}")
    public void deleteServiceRequest(@PathVariable("requestId") Long requestId)
    {
        service.deleteServiceRequest(requestId);
    }

    @RequestMapping(path ="/getRequestfilter/{sta}/{nbMedailles}/{date}", method = RequestMethod.GET)

    public List<Demande> getRequestService(@PathVariable String sta, @PathVariable String nbMedailles, @PathVariable String date)
    {

        Statut statut = Statut.valueOf(sta.toUpperCase());
        String jour = date.substring(0,2);
        String mois = date.substring(2,4);
        String annee = date.substring(4,8);
        String d = jour.concat("/"+mois+"/"+annee);
        Filtre f = new Filtre(statut,Integer.parseInt(nbMedailles), new Date(d));
        return service.filterRequest(f);
    }

    @PutMapping("/updateRequestService/{id}")
    public ResponseEntity<Demande> updateRequestService(@PathVariable Long id, @RequestBody Demande demande)
    {
        return service.updateRequestService(id,demande);
    }


}
