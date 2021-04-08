package com.project.givemehand.models;

import javax.persistence.*;

@Entity
@Table( name ="Demande")
public class Demande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Offre offre;
}
