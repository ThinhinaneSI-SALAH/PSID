package com.project.givemehand.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table ( name ="Offre")
public class Offre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Date dateOffre;
    private String villeOffre;
    @OneToOne
    private Note note;
    private double moyenneNotes = 0.0;
    private int nbMedailles;
    private Categorie cat;
    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "offre", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<Demande> demandes= new HashSet<>();


    public Offre(){

    }

    public Offre( String titre, String description, Date dateOffre, String villeOffre, int nbMedailles, Categorie cat, User user) {
        this.titre = titre;
        this.description = description;
        this.dateOffre = dateOffre;
        this.villeOffre = villeOffre;
        this.nbMedailles = nbMedailles;
        this.cat = cat;
        this.user = user;
        this.note = new Note(this);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOffre() {
        return dateOffre;
    }

    public void setDateOffre(Date dateOffre) {
        this.dateOffre = dateOffre;
    }

    public String getVilleOffre() {
        return villeOffre;
    }

    public void setVilleOffre(String villeOffre) {
        this.villeOffre = villeOffre;
    }

    public double getMoyenneNotes() {
        return moyenneNotes;
    }

    public void setMoyenneNotes(int moyenneNotes) {
        this.moyenneNotes = moyenneNotes;
    }

    public int getNbMedailles() {
        return nbMedailles;
    }

    public void setNbMedailles(int nbMedailles) {
        this.nbMedailles = nbMedailles;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public void addDemande(Demande demande){
        this.demandes.add(demande);
    }

    public void deleteDemande(Demande demande){
        this.demandes.remove(demande);
    }

    public void setNote(int note) {
        switch (note) {
            case 1:
                this.note.setNote1();
                break;
            case 2:
                this.note.setNote2();
                break;
            case 3:
                this.note.setNote3();
                break;
            case 4:
                this.note.setNote4();
                break;
            default:
                this.note.setNote5();
        }
    }

    public double calculMoyenne(){
        int somme = this.note.getNote1() + this.note.getNote2()*2 + this.note.getNote3()*3 +this.note.getNote4()*4 + this.note.getNote5()*5;
        int nbNotes = this.note.getNote1()+this.note.getNote2()+this.note.getNote3()+this.note.getNote4()+this.note.getNote5();

        this.moyenneNotes = somme/nbNotes;
        return this.moyenneNotes;
    }
}
