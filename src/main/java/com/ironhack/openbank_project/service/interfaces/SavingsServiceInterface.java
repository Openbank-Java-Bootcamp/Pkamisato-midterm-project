package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.utils.Money;

import java.math.BigDecimal;

public interface SavingsServiceInterface {

    Savings addSavings(Savings savings);

    Savings getSavingsById(Long id);

    void deleteSavings(Long id);

    Money getActualBalance(Long id);
    void setMinimumBalance(Long id, Money newMinimumBalance);

    BigDecimal setInterestRate(Long id, BigDecimal newInterestRate);

}
