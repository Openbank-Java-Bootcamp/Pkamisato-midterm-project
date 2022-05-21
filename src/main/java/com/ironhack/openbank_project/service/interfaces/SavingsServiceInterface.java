package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.utils.Money;

public interface SavingsServiceInterface {

    Savings addSavings(Savings savings);

    Savings getSavingsById(Long id);

    void deleteSavings(Long id);

    Money getActualBalance(Long id);

}
