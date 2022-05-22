package com.ironhack.openbank_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.openbank_project.utils.Money;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String secretKey;
    @NotNull
    private LocalDate creationDate;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Balance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Balance_fee_amount")),
    })
    private Money balance;

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
    private static final Money DEFAULT_PENALTY_FEE = new Money(new BigDecimal(40), Currency.getInstance("EUR"));
    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.of(2021,01,01);

    public Account(String secretKey, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner ) {
        this.secretKey = secretKey;
        this.creationDate = DEFAULT_CREATION_DATE;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee  = DEFAULT_PENALTY_FEE;;
    }


    public String sendTransfer(Money transferAmount) {
        if (balance.getAmount().compareTo(transferAmount.getAmount()) >= 0) {
            BigDecimal newBalanceAmount = balance.getAmount().subtract(transferAmount.getAmount());
            Money newBalance = new Money(newBalanceAmount, Currency.getInstance("EUR"));
            this.setBalance(newBalance);
            return ("Current Balance: "+ newBalance);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have enough balance to make transfers");
        }
    }

    public void receiveTransfer(Money transferAmount) {
        BigDecimal newBalanceAmount = balance.getAmount().add(transferAmount.getAmount());
        Money newBalance = new Money(newBalanceAmount, Currency.getInstance("EUR"));
        this.setBalance(newBalance);
    }


}
