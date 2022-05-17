package com.ironhack.openbank_project.model;

import com.ironhack.openbank_project.enums.Status;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private String secretKey;
    @NotNull

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Balance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Balance_fee_amount")),
    })
    private Money balance;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Minimum_balance_amount")),
    })
    private Money minimumBalance;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "primaryOwner_id")
    private AccountHolder primaryOwner;


    @ManyToOne
    @JoinColumn(name = "secondaryOwner_id")
    private AccountHolder secondaryOwner;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Penalty_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Penalty_fee_amount")),
    })
    private Money penaltyFee;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Monthly_maintenance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Monthly_maintenance_fee_amount")),
    })
    private Money monthlyMaintenanceFee;
    @NotNull
    private Status status;

    private static final Money DEFAULT_PENALTY_FEE = new Money(new BigDecimal(40), Currency.getInstance("EUR"));

    public Account(LocalDate creationDate, String secretKey, Money balance, Money minimumBalance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money monthlyMaintenanceFee, Status status) {
        this.creationDate = LocalDate.now();
        this.secretKey = secretKey;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = DEFAULT_PENALTY_FEE;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public void applyPenaltyFee(Money balance) {
        if (minimumBalance.getAmount().compareTo(balance.getAmount()) > 0) {
            this.balance.decreaseAmount(penaltyFee.getAmount());
        } else {
            this.balance = balance;
        }
    }

    public abstract void addInterestRate();

    public void sendTransfer(Money transferAmount) {
        if (balance.getAmount().compareTo(transferAmount.getAmount()) >= 0) {
            BigDecimal newBalanceAmount = balance.getAmount().subtract(transferAmount.getAmount());
            Money newBalance = new Money(newBalanceAmount, Currency.getInstance("EUR"));
        }
    }

    public void receiveTransfer(Money transferAmount) {
        BigDecimal newBalanceAmount = balance.getAmount().add(transferAmount.getAmount());
        Money newBalance = new Money(newBalanceAmount, Currency.getInstance("EUR"));
    }


}
