package com.project.givemehand.models.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table (name ="Adresse")
public class Adresse
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String zip;
    private  String city;
    private  String country;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "adresse",
            cascade = CascadeType.PERSIST)
    private Set<User> users =new HashSet<>();

    public Adresse(String street, String zip, String city, String country,Set<User> users) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.users=users;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
