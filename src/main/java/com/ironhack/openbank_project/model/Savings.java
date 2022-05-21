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


import static java.util.Currency.getInstance;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Entity
@NoArgsConstructor
@Data
public class Savings extends Account{
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Minimum_balance_amount")),
    })
    private Money minimumBalance;

    @NotNull
    private BigDecimal interestRate;
    @NotNull
    private Status status;

    private int interestRateCounter = 0;

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.0025);
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.5);

    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal(1000),getInstance("EUR"));

    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(100),getInstance("EUR"));

    private static final Status DEFAULT_STATUS = Status.ACTIVE;


    public Savings(String secretKey, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(secretKey, balance, primaryOwner, secondaryOwner);
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
        this.interestRate =  DEFAULT_INTEREST_RATE;
        this.status = Status.FROZEN;
    }


    public void setInterestRate(BigDecimal interestRate) {
       if(MAX_INTEREST_RATE.compareTo(interestRate) > 0){
           this.interestRate = interestRate;
       }else{
           System.err.println("Insert Interest rate Between 0.0025 and 0.5 ");
       }
    }

    public void applyPenaltyFee() {
        if (minimumBalance.getAmount().compareTo(super.getBalance().getAmount()) > 0) {
            BigDecimal newBalanceAmount = super.getBalance().decreaseAmount(getPenaltyFee().getAmount());
            Money newBalance = new Money(newBalanceAmount,getInstance("EUR"));
            super.setBalance(newBalance);
        }
    }

    public void setMinimumBalance(Money minimumBalance) {
       if(intValue(minimumBalance)< intValue(MIN_MINIMUM_BALANCE)){
           System.err.println("Balance can not be lees than 100€ ");
       }else{
           setMinimumBalance(minimumBalance);
       }
    }

    public void addInterestRate(){
        LocalDate actualDate = LocalDate.now();
        Period period = Period.between( getCreationDate(), actualDate);
        int years = period.getYears(); // number of years that have passed since the creation of the account
        // how many times interest rate has been paid
        int paidCounter = years - interestRateCounter;
        if(paidCounter != 0){
            BigDecimal amountWithInterestRate = super.getBalance().getAmount();
            amountWithInterestRate.add(amountWithInterestRate.multiply(interestRate));
            interestRateCounter++;
            int i = 1;
            while (i < paidCounter){
                amountWithInterestRate.add(amountWithInterestRate.multiply(interestRate));
                interestRateCounter++;
                i++;
            }
            setInterestRateCounter(years);
            Money newBalance = new  Money(amountWithInterestRate,getInstance("EUR"));
            super.setBalance(newBalance);
        }
    }

}
