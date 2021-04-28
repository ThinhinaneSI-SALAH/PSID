package com.project.givemehand.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="dd/MM/yyyy",timezone="Europe/France")
    private Date dateDemande;
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private User user;

    @ManyToOne
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

    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", dateDemande=" + dateDemande +
                ", statut='" + statut + '\'' +
                ", user=" + user +
                ", offre=" + offre +
                '}';
    }
}
