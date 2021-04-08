package com.project.givemehand.models.entity;

public enum Categorie {
    BRICOLAGE("Bricolage"), JARDINAGE("Jardinage"), GARDERIE("Garderie"), SPORT("Sport"),
    INFORMATIQUE("Informatique"), COURS_PARTICULIERS ("Cours_Particuliers") , DECORATION ("Décoration"),
    CUISINE("cuisine"), MENAGE("Ménage"), AUTRES("Autres");

    private String label;

    private Categorie(String lab){
        this.label = lab;
    }

}
