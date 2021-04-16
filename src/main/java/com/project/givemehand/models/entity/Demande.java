package com.project.givemehand.models.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * La classe demande represente les demandes des utilisateurs
 */
@Entity
@Table( name ="Demande")
public class Demande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date dateDemande;
    private Statut statut;
    @ManyToOne
    private Offre offre;


    @ManyToOne
    private User user;

    public Demande(){

    }

    /**
     *
     * @param dateDemande de l'offre
     * @param offre offre rattache a cette demande
     * @param user qui propose l'offre
     */
    public Demande( Date dateDemande, Offre offre,User user) {
        this.dateDemande = dateDemande;
        this.statut = Statut.ATTENTE;
        this.offre = offre;
        this.user =user;
    }


    public Date getDateDemande() {
        return dateDemande;
    }

    public Statut getStatut() {
        return statut;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}
