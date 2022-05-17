package com.ironhack.openbank_project.utils;


import com.sun.istack.NotNull;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    @NotNull
    private String streetName;
    @NotNull
    private int streetNumber;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private int postCode;

    public Address() {
    }

    public Address(String streetName, int streetNumber, String city, String country, int postCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }


    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNumber == address.streetNumber && postCode == address.postCode && Objects.equals(streetName, address.streetName) && Objects.equals(city, address.city) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, city, country, postCode);
    }
}
