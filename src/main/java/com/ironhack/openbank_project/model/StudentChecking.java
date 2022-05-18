package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class StudentChecking extends Account{

    @NotNull
    private String secretKey;
    @NotNull
    private Status status;

    public StudentChecking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee, String secretKey, Status status) {
        super(creationDate, balance, primaryOwner, secondaryOwner, penaltyFee);
        this.secretKey = secretKey;
        this.status = status;
    }
}
