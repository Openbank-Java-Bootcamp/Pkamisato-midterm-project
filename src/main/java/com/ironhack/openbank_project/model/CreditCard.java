package com.ironhack.openbank_project.model;

import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import static java.util.Currency.getInstance;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Entity
@NoArgsConstructor
@Data
public class CreditCard extends Account{

    @NotNull
    private Money creditLimit;
    @NotNull
    private BigDecimal interestRate;
    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.2);
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1);
    private static final Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal(100),getInstance("EUR"));
    private static final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000),getInstance("EUR"));


    public CreditCard(Date creationDate, String secretKey, Money balance, String primaryOwner, String secondaryOwner, Money penaltyFee, Status status, Money creditLimit, BigDecimal interestRate) {
        super(creationDate, secretKey, balance, primaryOwner, secondaryOwner, penaltyFee, status);
        this.creditLimit = DEFAULT_CREDIT_LIMIT;
        this.interestRate = DEFAULT_INTEREST_RATE;
    }

    public void setCreditLimit(Money creditLimit) {
       if(intValue(creditLimit) <= intValue(MAX_CREDIT_LIMIT)){
           this.creditLimit = creditLimit;
       }else{
           System.err.println("Credit Limit can not be moore than 100000€ ");
       }
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(intValue(interestRate) < intValue(MIN_INTEREST_RATE)){
            System.err.println("Interest can not be less than 0,1% ");
        }else{
        this.interestRate = interestRate;
        }
    }
}
