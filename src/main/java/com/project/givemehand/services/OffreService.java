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

/**
 * Cette classe contient les services de l'offre
 */
@Service
public class OffreService implements IOffre {

    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    /**
     * Permet de filtrer les offres avec les parametres recus
     * @param f represente les parametres du filtre

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
     * @return la liste d'offres existants dans la base

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

    public void save(Offre offres,Long id)
    {
        User user =userRepository.findById(id).get();
        offres.setUser(user);
        this.offreRepository.save(offres);
    }

    public void saveALL(Offre offres, User user)
    {   //offres.setUser(user)
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

    public Set<Offre> getOfferByUserId(Long id_user)
    {
        User user = this.userRepository.findById(id_user).get();
        Set<Offre> offres =  user.getOffres();
        return offres;
    }

    public Long getIdByEmail(String email)
    {
        User user =userRepository.findByEmail(email).get();
        Long id =user.getId();
        return id;
    }

    public Set<Offre> getOffreByEmail(String email)
    {
        User user =this.userRepository.findByEmail(email).get();
        Set<Offre> offre=user.getOffres();
        return offre;
    }
}



