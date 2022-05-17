package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;


@Entity
@Data
@NoArgsConstructor
public class StudentChecking extends Account{

    @Embedded
    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal(0), Currency.getInstance("EUR"));

    @Embedded
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(0), Currency.getInstance("EUR"));


    public StudentChecking(LocalDate creationDate, String secretKey, Money balance, Money minimumBalance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, secretKey, balance, minimumBalance = DEFAULT_MINIMUM_BALANCE , primaryOwner, secondaryOwner, monthlyMaintenanceFee = DEFAULT_MONTHLY_MAINTENANCE_FEE , status);
    }

    @Override
    public void addInterestRate() {
        throw new RuntimeException("Checking doesn't have interest rate");
    }
}
