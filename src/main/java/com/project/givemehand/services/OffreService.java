package com.project.givemehand.services;

import com.project.givemehand.interfaces.IOffre;
import com.project.givemehand.models.entity.Categorie;
import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;
import com.project.givemehand.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class OffreService implements IOffre {

    @Autowired
    private OffreRepository offreRepository;

    public List<Offre>  filtrerOffre(Filtre f){

        List<Offre>  offres = offreRepository.findAll();
        List<Offre> offresRetenues= new ArrayList<Offre>();

        for (Offre off : offres){
                    String catOffre = off.getCat();
                    Categorie catOffreFilter = f.getCategorie();
                    Date dateFinOffre = off.getDateFinOffre();
                    Date dateFiltre = f.getDateFiltre();
                    String villeOffre =  off.getVilleOffre();
                    String villeRecherche = f.getVille();
                    int medaillesOffre = off.getNbMedailles();
                    int medaillesFiltre = f.getMedailles();
                    String description = off.getDescription();
                    String titre = off.getTitre();
                    String motClesFiltre = f.getMotCles();

                    //System.out.println(dateFinOffre);
                    System.out.println(dateFinOffre.before(dateFiltre));

                    if(catOffre.toUpperCase().equals(catOffreFilter.toString().toUpperCase())
                            && villeOffre.toUpperCase().equals(villeRecherche.toUpperCase())
                            && (medaillesOffre <= medaillesFiltre)
                            && ( (description.toUpperCase().contains(motClesFiltre.toUpperCase())) || (titre.toUpperCase().contains(motClesFiltre.toUpperCase())))
                            && ( dateFinOffre.before(dateFiltre) || dateFinOffre.equals(dateFiltre)) )
                    {
                            offresRetenues.add(off);

                    }
                }

        return offresRetenues;
    }
    public List<Offre> getAlloffres(){
        return offreRepository.findAll();
    }
}
