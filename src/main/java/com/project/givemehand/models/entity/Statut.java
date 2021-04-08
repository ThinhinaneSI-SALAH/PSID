package com.project.givemehand.models.entity;

public enum Statut {

        ATTENTE("En Attente"), ACCEPTE("ACCEPTE"), REFUSE("REFUSE");

        private String label;

        private Statut(String lab){
            this.label = lab;
        }


}
