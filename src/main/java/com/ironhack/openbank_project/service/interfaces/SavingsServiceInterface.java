package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.Savings;

public interface SavingsServiceInterface {

    Savings addSavings(Savings savings);
    Savings getSavingsById(Long id);
    Savings updateSavings(Long id, Savings savings );
    void deleteSavings(Long id);
}
