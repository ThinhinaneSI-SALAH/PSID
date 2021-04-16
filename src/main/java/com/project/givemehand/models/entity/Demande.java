package com.project.givemehand.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String statut;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre;

    public Demande(){

    }

    /**
     *
     * @param dateDemande de l'offre
     * @param offre offre rattache a cette demande
     * @param user qui propose l'offre
     */
    public Demande( Date dateDemande,Statut sta ,Offre offre,User user) {
        this.dateDemande = dateDemande;
        this.statut = sta.toString();
        this.offre = offre;
        this.user =user;
    }


    public Date getDateDemande() {
        return dateDemande;
    }

    public String getStatut() {
        return statut;
    }

    public Offre getOffre() {
        return offre;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setStatut(Statut statut) {
        this.statut = statut.toString();
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
