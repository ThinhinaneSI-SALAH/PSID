package com.project.givemehand.services;

import com.project.givemehand.interfaces.IOffre;
import com.project.givemehand.models.entity.*;
import com.project.givemehand.repository.OffreRepository;
import com.project.givemehand.repository.UserRepository;
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
    private UserRepository userRepository;

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

    public List<Offre> getAlloffresbyuser(User user) {
        Optional<User> user1=userRepository.findById(user.getId());
        return offreRepository.findAll();
    }

    public Offre getOfferById(long id)
    {
        return this.offreRepository.findById(id).get();
    }

    public void save(Offre offres) {
        //offres.setUser(user);
        this.offreRepository.save(offres);
    }

    public void saveALL(Offre offres, User user)
    {
        //offres.setUser(user)
        offres.setUser(user);
        this.offreRepository.save(offres);
    }

    public void deleteoffer(long id) {
        this.offreRepository.deleteById(id);
    }

    public Offre update(Offre offres) {
        //Optional<Offre> offreg = offreRepository.findById(id);
        return offreRepository.save(offres);


    }

    public List<Offre> getOfferByUser(Long id)
    {
        User user =userRepository.findById(id).get();
         return offreRepository.findByUser(user);
    }
}



