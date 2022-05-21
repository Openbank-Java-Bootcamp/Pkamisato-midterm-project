package com.ironhack.openbank_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.openbank_project.utils.Address;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User{


    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "AccountHolders_address_Street_Address")),
            @AttributeOverride(name = "streetNumber", column = @Column(name = "AccountHolders_address_Street_number")),
            @AttributeOverride(name = "city", column = @Column(name = "AccountHolders_address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "AccountHolders_address_country")),
            @AttributeOverride(name = "postCode", column = @Column(name = "AccountHolders_address_postalCode"))
    })
    private Address address;

    @Email
    private String mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    List<Account> accountList = new ArrayList<>();

    public AccountHolder(String name, String username, String password, Collection<Role> roles, LocalDate dateOfBirth, Address address, String mailingAddress) {
        super(name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }
}
