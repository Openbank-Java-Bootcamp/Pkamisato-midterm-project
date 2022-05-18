package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
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

    @NotNull
    private String secretKey;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Minimum_balance_amount")),
    })
    private Money minimumBalance;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Monthly_maintenance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Monthly_maintenance_fee_amount")),
    })
    private Money monthlyMaintenanceFee;
    @NotNull
    private Status status;
    @Embedded
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12), Currency.getInstance("EUR"));
    @Embedded
    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(250), Currency.getInstance("EUR"));

    private int MaintenanceFeeCounter = 0;

    public Checking(LocalDate creationDate, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee, String secretKey, Status status) {
        super(creationDate, balance, primaryOwner, secondaryOwner, penaltyFee);
        this.secretKey = secretKey;
        this.minimumBalance = MIN_MINIMUM_BALANCE ;
        this.monthlyMaintenanceFee = DEFAULT_MONTHLY_MAINTENANCE_FEE;
        this.status = status;
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
