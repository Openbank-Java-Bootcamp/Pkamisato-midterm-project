package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Currency;

import static java.util.Currency.getInstance;

@Entity
@Data
@NoArgsConstructor
public class Checking extends Account{
    @Embedded
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12), Currency.getInstance("EUR"));
    @Embedded
    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(250), Currency.getInstance("EUR"));

    private int MaintenanceFeeCounter = 0;

    public Checking(LocalDate creationDate, String secretKey, Money balance, Money minimumBalance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money monthlyMaintenanceFee, Status status) {
        super(creationDate, secretKey, balance, minimumBalance = MIN_MINIMUM_BALANCE , primaryOwner, secondaryOwner, monthlyMaintenanceFee =DEFAULT_MONTHLY_MAINTENANCE_FEE , status);
    }

    @Override
    public void addInterestRate() {
        throw new RuntimeException("Checking doesn't have interest rate");
    }

    public void deductMonthlyMaintenanceFee(Money balance, Money monthlyMaintenanceFee , LocalDate actualDate){
        Period period = Period.between(actualDate, getCreationDate());
        int months = period.getMonths(); // number of months that have passed since the creation of the account
        // how many times maintenance fee has been paid
        int notPaidCounter = months - getMaintenanceFeeCounter();
        if(notPaidCounter != 0){
            BigDecimal totalMaintenanceFee = monthlyMaintenanceFee.getAmount().multiply(new BigDecimal(notPaidCounter));
            BigDecimal newBalanceAmount = balance.decreaseAmount(totalMaintenanceFee);
            Money newBalance = new Money(newBalanceAmount,getInstance("EUR"));
            super.setBalance(newBalance);
            setMaintenanceFeeCounter(notPaidCounter);
        }
    }



}
