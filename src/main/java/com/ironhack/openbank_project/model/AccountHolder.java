package com.ironhack.openbank_project.model;

import com.ironhack.openbank_project.utils.Address;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User{


    @NotNull
    private Date dateOfBirth;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "AccountHolders_address_Street_Address")),
            @AttributeOverride(name = "streetNumber", column = @Column(name = "AccountHolders_address_Street_number")),
            @AttributeOverride(name = "city", column = @Column(name = "AccountHolders_address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "AccountHolders_address_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "AccountHolders_address_postalCode"))
    })
    private Address address;

    @Email
    private String mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    List<Account> accountList = new ArrayList<>();

}
