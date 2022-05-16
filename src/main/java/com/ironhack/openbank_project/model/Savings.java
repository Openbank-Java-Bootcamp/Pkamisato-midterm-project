package com.ironhack.openbank_project.model;


import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.math.BigDecimal;
import java.util.Date;


import static java.util.Currency.getInstance;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Entity
@NoArgsConstructor
@Data
public class Savings extends Account{

    @NotNull
    private BigDecimal monthlyMaintenanceFee;
    @NotNull
    private Money minimumBalance;
    @NotNull
    private BigDecimal interestRate;

    private static final BigDecimal DEFAULT_MONTHLY_MAINTENANCE_FEE = new BigDecimal(0);
    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.0025);
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.5);
    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal(1000),getInstance("EUR"));
    private static final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(100),getInstance("EUR"));


    public Savings(Date creationDate, String secretKey, Money balance, String primaryOwner, String secondaryOwner, Money penaltyFee, Status status, BigDecimal monthlyMaintenanceFee, Money minimumBalance, BigDecimal interestRate) {
        super(creationDate, secretKey, balance, primaryOwner, secondaryOwner, penaltyFee, status);
        this.monthlyMaintenanceFee = DEFAULT_MONTHLY_MAINTENANCE_FEE;
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
        this.interestRate = DEFAULT_INTEREST_RATE;
    }

    public void setInterestRate(BigDecimal interestRate) {
       if (intValue(interestRate) <= intValue(MAX_INTEREST_RATE)){
           this.interestRate = interestRate;
       }else{
           System.err.println("Insert Interest rate Between 0.0025 and 0.5 ");
       }
    }

    public void setMinimumBalance(Money minimumBalance) {
       if(intValue(minimumBalance)< intValue(MIN_MINIMUM_BALANCE)){
           System.err.println("Balance can not be lees than 100€ ");
       }else{
           this.minimumBalance = minimumBalance;
       }
    }
}
