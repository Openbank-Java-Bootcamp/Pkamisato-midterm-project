package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.Savings;
import com.ironhack.openbank_project.repository.SavingsRepository;
import com.ironhack.openbank_project.service.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;

    public Savings addSavings(Savings savings){
        return savingsRepository.save(savings);
    }
    public Savings getSavingsById(Long id){
        return savingsRepository.findById(id).get();
    }
    public Savings updateSavings(Long id, Savings savings ){
        Optional<Savings> savingsFromDb = savingsRepository.findById(id);
        savings.setId(savingsFromDb.get().getId());
        return savingsRepository.save(savings);
    }
    public void deleteSavings(Long id){
        Optional<Savings> foundSavings = savingsRepository.findById(id);
        savingsRepository.delete(foundSavings.get());
    }
}
