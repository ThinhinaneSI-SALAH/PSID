package com.project.givemehand.models;

import javax.persistence.*;

@Entity
@Table(	name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
