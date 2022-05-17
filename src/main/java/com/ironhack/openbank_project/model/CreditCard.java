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
@NoArgsConstructor
@Data
public class CreditCard extends Account{

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Credit_limit_amount")),
    })
    private Money creditLimit;
    @NotNull
    @Column(precision = 1,scale = 1)
    private BigDecimal interestRate;

    private int interestRateCounter = 0;
    @Embedded
    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal(0), Currency.getInstance("EUR"));

    @Embedded
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(0), Currency.getInstance("EUR"));

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.2);

    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1);
    @Embedded
    private static final Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal(100),getInstance("EUR"));
    @Embedded
    private static final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000),getInstance("EUR"));

    public CreditCard(LocalDate creationDate, String secretKey, Money balance, Money minimumBalance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money monthlyMaintenanceFee, Status status, Money creditLimit, BigDecimal interestRate) {
        super(creationDate, secretKey, balance, minimumBalance = DEFAULT_MINIMUM_BALANCE , primaryOwner, secondaryOwner, monthlyMaintenanceFee = DEFAULT_MONTHLY_MAINTENANCE_FEE , status);
        this.creditLimit = DEFAULT_CREDIT_LIMIT;
        this.interestRate = DEFAULT_INTEREST_RATE;
    }

    public void setCreditLimit(Money creditLimit) {
       if(MAX_CREDIT_LIMIT.getAmount().compareTo(creditLimit.getAmount()) >= 0){
           this.creditLimit = creditLimit;
       }else{
           System.err.println("Credit Limit can not be moore than 100000€ ");
       }
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(MIN_INTEREST_RATE.compareTo(interestRate) > 0){
            System.err.println("Interest can not be less than 0,1% ");
        }else{
        this.interestRate = interestRate;
        }
    }

    @Override
    public void addInterestRate(){
        LocalDate actualDate = LocalDate.now();
        Period period = Period.between(actualDate, getCreationDate());
        int months = period.getMonths(); // number of months that have passed since the creation of the account
        // how many times interest rate has been paid
        int paidCounter = months - interestRateCounter;
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
            Money newBalance = new  Money(amountWithInterestRate,getInstance("EUR"));
            super.setBalance(newBalance);
        }
    }

}
