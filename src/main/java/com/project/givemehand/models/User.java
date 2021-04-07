package com.project.givemehand.models;

import javax.persistence.*;

@Entity
@Table(	name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private int medailles;

    public User(){

    }
}
