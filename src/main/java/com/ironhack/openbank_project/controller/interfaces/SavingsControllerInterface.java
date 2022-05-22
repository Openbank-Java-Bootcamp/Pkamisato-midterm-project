package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;


public interface SavingsControllerInterface {
    Savings addSavingsAccount(Savings savings);

    Savings getSavingsAccountById(Long id);

    void deleteSavingsAccount(Long id);

    Money getActualBalance(Long id);
    void updateMinimumBalance(Long id, Money newMinimBalance);

    public BigDecimal updateInterestRate( Long id, BigDecimal newInterestRate);
}
