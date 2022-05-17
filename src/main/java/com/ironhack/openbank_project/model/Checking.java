package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Checking extends Account{
    @Embedded
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12), Currency.getInstance("EUR"));
    @Embedded
    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(250), Currency.getInstance("EUR"));

    public Checking(Date creationDate, String secretKey, Money balance, Money minimumBalance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, secretKey, balance, minimumBalance = MIN_MINIMUM_BALANCE , primaryOwner, secondaryOwner, monthlyMaintenanceFee =DEFAULT_MONTHLY_MAINTENANCE_FEE , status);
    }

}
