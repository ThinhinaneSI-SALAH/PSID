package com.project.givemehand.models.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Adresse
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String zip;
    private  String city;
    private  String country;

    public Adresse(String street, String zip, String city, String country) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
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
}
