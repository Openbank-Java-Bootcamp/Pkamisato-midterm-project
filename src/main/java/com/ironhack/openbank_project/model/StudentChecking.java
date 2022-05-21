package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class StudentChecking extends Account{
    @NotNull
    private Status status;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;

    public StudentChecking(String secretKey, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        this.status = DEFAULT_STATUS;
    }
}
