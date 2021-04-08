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
@Service
public class OffreService implements IOffre {
    @Autowired
    private OffreRepository offreRepository;

    public List<Offre>  filtrerOffre(Filtre f){

        List<Offre>  offres = offreRepository.findAll();
        List<Offre> offresRetenues= new ArrayList<Offre>();

        for (Offre off : offres){
                    Categorie catOffre = off.getCat();
                    Categorie catOffreFilter = f.getCategorie();
                    Date dateFinOffre = off.getDateFinOffre();
                    Date dateFiltre = f.getDateFiltre();
                    String villeOffre = off.getUser().getAdresse().getCity();
                    String villeRecherche = f.getVille();
                    int medaillesOffre = off.getNbMedailles();
                    int medaillesFiltre = f.getMedailles();
                    String description = off.getDescription();
                    String titre = off.getTitre();
                    String motClesFiltre = f.getMotCles();
                    if(catOffre.equals(catOffreFilter) && ( dateFinOffre.after(dateFiltre) || dateFinOffre.equals(dateFiltre))
                        && villeOffre.equals(villeRecherche) && medaillesOffre<=medaillesFiltre
                                 &&( description.contains(motClesFiltre) || titre.contains(motClesFiltre) ))
                    {
                            offresRetenues.add(off);

                    }
                }

        return offresRetenues;
    }
}
