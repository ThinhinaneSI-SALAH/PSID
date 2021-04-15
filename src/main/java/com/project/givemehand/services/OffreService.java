package com.project.givemehand.services;

import com.project.givemehand.interfaces.IOffre;
import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
/**
 * Cette classe contient les services de l'offre
 */
public class OffreService implements IOffre {

    @Autowired
    private OffreRepository offreRepository;

    /**
     * Permet de filtrer les offres avec les parametres recus
     *
     * @param f
     * @return une liste d'offre
     */
    public List<Offre> filtrerOffre(Filtre f) {

        List<Offre> offres = offreRepository.findAll();
        List<Offre> offresRetenues = new ArrayList<Offre>();

        for (Offre off : offres) {
            String catOffre = off.getCat();
            Categorie catOffreFilter = f.getCategorie();
            Date dateFinOffre = off.getDateFinOffre();
            Date dateFiltre = f.getDateFiltre();
            String villeOffre = off.getVilleOffre();
            String villeRecherche = f.getVille();
            int medaillesOffre = off.getNbMedailles();
            int medaillesFiltre = f.getMedailles();
            String description = off.getDescription();
            String titre = off.getTitre();
            String motClesFiltre = f.getMotCles();

            //System.out.println(dateFinOffre);
            System.out.println(dateFinOffre.before(dateFiltre));

            if (catOffre.toUpperCase().equals(catOffreFilter.toString().toUpperCase())
                    && villeOffre.toUpperCase().equals(villeRecherche.toUpperCase())
                    && (medaillesOffre <= medaillesFiltre)
                    && ((description.toUpperCase().contains(motClesFiltre.toUpperCase())) || (titre.toUpperCase().contains(motClesFiltre.toUpperCase())))
                    && (dateFinOffre.before(dateFiltre) || dateFinOffre.equals(dateFiltre))) {
                offresRetenues.add(off);

            }
        }

        return offresRetenues;
    }

    /**
     * Renvoie la liste des offres sans filtre
     *
     * @return
     */
    public List<Offre> getAlloffres() {
        return offreRepository.findAll();
    }

    public Offre getOfferById(long id)
    {
        return this.offreRepository.findById(id).get();
    }

    public void save(Offre offres) {
        this.offreRepository.save(offres);
    }

    public void deleteoffer(long id) {
        this.offreRepository.deleteById(id);
    }

    public Offre update(Offre offres) {
        //Optional<Offre> offreg = offreRepository.findById(id);
        return offreRepository.save(offres);
        /*if (offreg.isPresent()) {
            Offre offrestest = offreg.get();
           // offrestest.setCat(offres.getCat());
            offrestest.setDateFinOffre(offres.getDateFinOffre());
            offrestest.setDateOffre(offres.getDateOffre());
            offrestest.setNbMedailles(offres.getNbMedailles());
            offrestest.setTitre(offres.getTitre());
            offrestest.setDescription(offres.getDescription());
            offrestest.setMoyenneNotes((int) offres.getMoyenneNotes());
            offrestest.setVilleOffre(offres.getVilleOffre());
            offrestest.setUser(offres.getUser());
            return new ResponseEntity<>( this.offreRepository.save(offrestest), HttpStatus.OK);
        } else {
            System.out.println("Error Offre is not found");
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

    }
}



