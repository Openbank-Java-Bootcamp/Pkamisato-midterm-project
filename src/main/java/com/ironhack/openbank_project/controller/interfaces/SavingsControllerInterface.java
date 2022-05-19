package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.utils.Money;


public interface SavingsControllerInterface {
    Savings addSavingsAccount(Savings savings);
    Savings getSavingsAccountById( Long id);
    Savings updateSavingsAccount( Long id,Savings savings );
    void deleteSavingsAccount(Long id);
    Money getActualBalance( Long id);
}
