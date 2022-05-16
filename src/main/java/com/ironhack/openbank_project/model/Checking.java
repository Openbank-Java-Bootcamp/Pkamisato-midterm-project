package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Checking extends Account{

    @NotNull
    private Money monthlyMaintenanceFee;
    @NotNull
    private Money minimumBalance;
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12), Currency.getInstance("EUR"));
    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(250), Currency.getInstance("EUR"));



    public Checking(Date creationDate, String secretKey, Money balance, String primaryOwner, String secondaryOwner, Money penaltyFee, Status status, Money monthlyMaintenanceFee, Money minimumBalance) {
        super(creationDate, secretKey, balance, primaryOwner, secondaryOwner, penaltyFee, status);
        this.monthlyMaintenanceFee = DEFAULT_MONTHLY_MAINTENANCE_FEE;
        this.minimumBalance = MIN_MINIMUM_BALANCE;

    }
}
