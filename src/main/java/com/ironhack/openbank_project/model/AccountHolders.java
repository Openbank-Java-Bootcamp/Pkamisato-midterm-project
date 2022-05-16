package com.ironhack.openbank_project.model;

import com.ironhack.openbank_project.utils.Address;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolders extends User{

    @NotNull
    private Date dateOfBirth;
    @NotNull
    private Address address;

    private String mailingAddress;

}
