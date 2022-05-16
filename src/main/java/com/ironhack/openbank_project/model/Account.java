package com.ironhack.openbank_project.model;

import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract  class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Date creationDate;

    @NotNull
    private String secretKey;
    @NotNull
    private Money balance;
    @NotNull
    private String primaryOwner;

    private String secondaryOwner;
    @NotNull
    private Money penaltyFee;
    @NotNull
    private Status status;

    private static final Money DEFAULT_PENALTY_FEE = new Money(new BigDecimal(40), Currency.getInstance("EUR"));

    public Account(Date creationDate, String secretKey, Money balance, String primaryOwner, String secondaryOwner, Money penaltyFee, Status status) {
        this.creationDate = creationDate;
        this.secretKey = secretKey;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = DEFAULT_PENALTY_FEE;
        this.status = status;
    }


}
